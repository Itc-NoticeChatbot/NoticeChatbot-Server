package com.inhatc.noticebot.domain.notice.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class NoticeDuplicatedException extends DomainException {

	public NoticeDuplicatedException(String sourceUrl) {
		super(ErrorCode.NOTICE_DUPLICATED, "중복된 공지 URL입니다: " + sourceUrl);
	}
}
