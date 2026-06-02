package com.inhatc.noticebot.notice.service;

import com.inhatc.noticebot.domain.notice.Notice;
import com.inhatc.noticebot.domain.notice.exception.NoticeNotFoundException;
import com.inhatc.noticebot.notice.crawler.CrawledNotice;
import com.inhatc.noticebot.notice.crawler.InhatcNoticeCrawler;
import com.inhatc.noticebot.notice.dto.CrawlResultResponse;
import com.inhatc.noticebot.notice.dto.NoticeResponse;
import com.inhatc.noticebot.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class NoticeServiceImpl implements NoticeService {

  private static final Logger log = LoggerFactory.getLogger(NoticeServiceImpl.class);

  private final InhatcNoticeCrawler crawler;
  private final NoticeRepository noticeRepository;
  private final TransactionTemplate transactionTemplate;

  public NoticeServiceImpl(
      InhatcNoticeCrawler crawler,
      NoticeRepository noticeRepository,
      TransactionTemplate transactionTemplate) {
    this.crawler = crawler;
    this.noticeRepository = noticeRepository;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public CrawlResultResponse crawlAndSave() {
    List<CrawledNotice> crawled = crawler.crawlAll();
    return transactionTemplate.execute(status -> saveAll(crawled));
  }

  @Override
  @Transactional(readOnly = true)
  public List<NoticeResponse> getList() {
    return noticeRepository.findAll().stream().map(NoticeResponse::convert).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public NoticeResponse get(Long id) {
    Notice notice =
        noticeRepository.findById(id).orElseThrow(() -> new NoticeNotFoundException(id));
    return NoticeResponse.convert(notice);
  }

  private CrawlResultResponse saveAll(List<CrawledNotice> crawled) {
    int saved = 0;
    int updated = 0;
    int skipped = 0;
    int failed = 0;

    for (CrawledNotice item : crawled) {
      try {
        Optional<Notice> existing = noticeRepository.findBySourceUrl(item.sourceUrl());
        if (existing.isEmpty()) {
          noticeRepository.save(convertToEntity(item));
          saved++;
        } else {
          Notice notice = existing.get();
          if (hasChanged(notice, item)) {
            updateNotice(notice, item);
            updated++;
          } else {
            skipped++;
          }
        }
      } catch (Exception ex) {
        log.error("공지 저장 실패: url={}, error={}", item.sourceUrl(), ex.getMessage());
        failed++;
      }
    }

    log.info(
        "크롤링 완료 - saved={}, updated={}, skipped={}, failed={}", saved, updated, skipped, failed);
    return CrawlResultResponse.of(saved, updated, skipped, failed);
  }

  private boolean hasChanged(Notice existing, CrawledNotice incoming) {
    return !existing.getTitle().equals(incoming.title())
        || !existing.getContent().equals(incoming.content());
  }

  private void updateNotice(Notice notice, CrawledNotice incoming) {
    notice.setTitle(incoming.title());
    notice.setContent(incoming.content());
    notice.setCategory(incoming.category());
    notice.setPublishedAt(incoming.publishedAt());
    notice.setUpdatedAt(LocalDateTime.now());
  }

  private Notice convertToEntity(CrawledNotice item) {
    LocalDateTime now = LocalDateTime.now();
    return new Notice(
        item.sourceSite(),
        item.sourceUrl(),
        item.title(),
        item.category(),
        item.content(),
        item.publishedAt(),
        now,
        now);
  }
}
