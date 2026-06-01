package com.inhatc.noticebot.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "잘못된 요청입니다."),
	VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED", "입력값 검증에 실패했습니다."),
	NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTICE_NOT_FOUND", "공지 데이터를 찾을 수 없습니다."),
	NOTICE_DUPLICATED(HttpStatus.CONFLICT, "NOTICE_DUPLICATED", "중복된 공지 데이터입니다."),
	CRAWL_SOURCE_UNREACHABLE(HttpStatus.SERVICE_UNAVAILABLE, "CRAWL_SOURCE_UNREACHABLE", "크롤링 대상에 접근할 수 없습니다."),
	CRAWL_PARSE_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "CRAWL_PARSE_FAILED", "크롤링 파싱에 실패했습니다."),
	AI_RESPONSE_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "AI_RESPONSE_FAILED", "AI 응답 처리에 실패했습니다."),
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 내부 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
