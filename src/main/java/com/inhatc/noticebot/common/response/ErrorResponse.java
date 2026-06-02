package com.inhatc.noticebot.common.response;

public record ErrorResponse(boolean success, String errorCode, String message, Object details) {

  public static ErrorResponse of(String errorCode, String message, Object details) {
    return new ErrorResponse(false, errorCode, message, details);
  }
}
