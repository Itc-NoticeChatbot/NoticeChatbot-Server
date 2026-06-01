package com.inhatc.noticebot.domain.notice.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class NoticeNotFoundException extends DomainException {

	public NoticeNotFoundException(Long noticeId) {
		super(ErrorCode.NOTICE_NOT_FOUND, "공지 데이터를 찾을 수 없습니다. id=" + noticeId);
	}
}
