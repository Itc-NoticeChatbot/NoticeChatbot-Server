package com.inhatc.noticebot.notice.dto;

import com.inhatc.noticebot.domain.notice.Notice;
import java.time.LocalDateTime;

public record NoticeResponse(
    Long id,
    String sourceSite,
    String sourceUrl,
    String title,
    String category,
    String content,
    LocalDateTime publishedAt,
    LocalDateTime crawledAt) {

  public static NoticeResponse convert(Notice notice) {
    return new NoticeResponse(
        notice.getId(),
        notice.getSourceSite(),
        notice.getSourceUrl(),
        notice.getTitle(),
        notice.getCategory(),
        notice.getContent(),
        notice.getPublishedAt(),
        notice.getCrawledAt());
  }
}
