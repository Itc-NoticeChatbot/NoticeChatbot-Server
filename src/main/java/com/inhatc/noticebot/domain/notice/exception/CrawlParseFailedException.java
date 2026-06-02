package com.inhatc.noticebot.domain.notice.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class CrawlParseFailedException extends DomainException {

  public CrawlParseFailedException(String url) {
    super(ErrorCode.CRAWL_PARSE_FAILED, "크롤링 파싱에 실패했습니다. url=" + url);
  }
}
