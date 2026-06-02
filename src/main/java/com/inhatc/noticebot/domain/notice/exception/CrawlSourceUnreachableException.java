package com.inhatc.noticebot.domain.notice.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class CrawlSourceUnreachableException extends DomainException {

  public CrawlSourceUnreachableException(String url) {
    super(ErrorCode.CRAWL_SOURCE_UNREACHABLE, "크롤링 대상에 접근할 수 없습니다. url=" + url);
  }
}
