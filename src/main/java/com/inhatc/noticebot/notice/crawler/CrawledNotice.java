package com.inhatc.noticebot.notice.crawler;

import java.time.LocalDateTime;

public record CrawledNotice(
    String sourceSite,
    String sourceUrl,
    String title,
    String category,
    String content,
    LocalDateTime publishedAt) {}
