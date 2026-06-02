package com.inhatc.noticebot.bookmark.dto;

import com.inhatc.noticebot.domain.bookmark.Bookmark;
import java.time.LocalDateTime;

public record BookmarkResponse(
    Long id, Long noticeId, String noticeTitle, String question, LocalDateTime createdAt) {

  public static BookmarkResponse convert(Bookmark bookmark) {
    return new BookmarkResponse(
        bookmark.getId(),
        bookmark.getNotice().getId(),
        bookmark.getNotice().getTitle(),
        bookmark.getQuestion(),
        bookmark.getCreatedAt());
  }
}
