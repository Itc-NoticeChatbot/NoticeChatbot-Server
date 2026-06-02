package com.inhatc.noticebot.notice.dto;

public record CrawlResultResponse(int saved, int updated, int skipped, int failed) {

  public static CrawlResultResponse of(int saved, int updated, int skipped, int failed) {
    return new CrawlResultResponse(saved, updated, skipped, failed);
  }
}
