package com.inhatc.noticebot.domain.chat.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class AiResponseFailedException extends DomainException {

  public AiResponseFailedException() {
    super(ErrorCode.AI_RESPONSE_FAILED, "AI 응답 처리에 실패했습니다.");
  }
}
