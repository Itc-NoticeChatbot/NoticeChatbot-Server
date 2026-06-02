package com.inhatc.noticebot.common.exception;

public class DomainException extends RuntimeException {

  private final ErrorCode errorCode;
  private final Object details;

  public DomainException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.details = null;
  }

  public DomainException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
    this.details = null;
  }

  public DomainException(ErrorCode errorCode, String message, Object details) {
    super(message);
    this.errorCode = errorCode;
    this.details = details;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public Object getDetails() {
    return details;
  }
}
