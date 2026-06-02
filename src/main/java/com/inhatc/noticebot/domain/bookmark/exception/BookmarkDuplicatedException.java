package com.inhatc.noticebot.domain.bookmark.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class BookmarkDuplicatedException extends DomainException {

  public BookmarkDuplicatedException(Long noticeId, String question) {
    super(
        ErrorCode.BOOKMARK_DUPLICATED,
        "중복된 북마크입니다. noticeId=" + noticeId + ", question=" + question);
  }
}
