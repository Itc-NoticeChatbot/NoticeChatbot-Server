package com.inhatc.noticebot.domain.bookmark.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class BookmarkNotFoundException extends DomainException {

  public BookmarkNotFoundException(Long bookmarkId) {
    super(ErrorCode.BOOKMARK_NOT_FOUND, "북마크 데이터를 찾을 수 없습니다. id=" + bookmarkId);
  }
}
