package com.inhatc.noticebot.notice.crawler;

import com.inhatc.noticebot.domain.notice.exception.CrawlParseFailedException;
import com.inhatc.noticebot.domain.notice.exception.CrawlSourceUnreachableException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InhatcNoticeCrawler {

  private static final Logger log = LoggerFactory.getLogger(InhatcNoticeCrawler.class);

  private static final String SOURCE_SITE = "inhatc";
  private static final String SEED_URL = "https://www.inhatc.ac.kr/kr/460/subview.do";
  private static final Pattern ARTICLE_URL_PATTERN =
      Pattern.compile("^https://www\\.inhatc\\.ac\\.kr/bbs/kr/11/\\d+/artclView\\.do.*$");
  private static final int CONNECT_TIMEOUT_MS = 5_000;
  private static final int MAX_RETRY_COUNT = 2;

  public List<CrawledNotice> crawlAll() {
    Document seedDoc = fetchWithRetry(SEED_URL);
    List<String> articleUrls = extractArticleUrls(seedDoc);

    List<CrawledNotice> results = new ArrayList<>();
    for (String url : articleUrls) {
      try {
        Document doc = fetchWithRetry(url);
        CrawledNotice notice = parse(url, doc);
        results.add(notice);
      } catch (CrawlParseFailedException | CrawlSourceUnreachableException ex) {
        log.error("공지 수집 실패: url={}, error={}", url, ex.getMessage());
      }
    }
    return results;
  }

  private List<String> extractArticleUrls(Document seedDoc) {
    List<String> urls = new ArrayList<>();
    Elements links = seedDoc.select("a[href]");
    for (Element link : links) {
      String href = link.attr("abs:href");
      String normalized = NoticeUrlNormalizer.normalize(href);
      if (normalized != null && ARTICLE_URL_PATTERN.matcher(normalized).matches()) {
        urls.add(normalized);
      }
    }
    return urls;
  }

  private CrawledNotice parse(String url, Document doc) {
    try {
      String title = doc.select(".artclViewTitle, h3.tit").text().strip();
      String content = doc.select(".artclView, .view_con").text().strip();
      String category = doc.select(".artclViewBrdNm, .category").text().strip();
      String dateText = doc.select(".artclViewWrDt, .date").text().strip();

      if (title.isEmpty() || content.isEmpty()) {
        throw new CrawlParseFailedException(url);
      }

      LocalDateTime publishedAt = parseDate(dateText);
      String normalizedCategory = category.isEmpty() ? null : category;

      return new CrawledNotice(SOURCE_SITE, url, title, normalizedCategory, content, publishedAt);
    } catch (CrawlParseFailedException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new CrawlParseFailedException(url);
    }
  }

  private LocalDateTime parseDate(String dateText) {
    if (dateText == null || dateText.isEmpty()) return null;
    String cleaned = dateText.replaceAll("\\.$", "").strip();
    List<String> patterns = List.of("yyyy-MM-dd", "yyyy.MM.dd");
    for (String pattern : patterns) {
      try {
        return LocalDateTime.parse(
            cleaned + " 00:00", DateTimeFormatter.ofPattern(pattern + " HH:mm"));
      } catch (DateTimeParseException ignored) {
      }
    }
    return null;
  }

  private Document fetchWithRetry(String url) {
    int attempts = 0;
    while (attempts <= MAX_RETRY_COUNT) {
      try {
        return Jsoup.connect(url).timeout(CONNECT_TIMEOUT_MS).maxBodySize(0).get();
      } catch (IOException ex) {
        attempts++;
        if (attempts > MAX_RETRY_COUNT) {
          throw new CrawlSourceUnreachableException(url);
        }
        long backoff = (long) Math.pow(2, attempts) * 500;
        try {
          Thread.sleep(backoff);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          throw new CrawlSourceUnreachableException(url);
        }
      }
    }
    throw new CrawlSourceUnreachableException(url);
  }
}
