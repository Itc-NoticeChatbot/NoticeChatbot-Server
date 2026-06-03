package com.inhatc.noticebot.domain.chat.exception;

import com.inhatc.noticebot.common.exception.DomainException;
import com.inhatc.noticebot.common.exception.ErrorCode;

public class ChatHistoryNotFoundException extends DomainException {

  public ChatHistoryNotFoundException(Long chatHistoryId) {
    super(ErrorCode.CHAT_HISTORY_NOT_FOUND, "대화 이력 데이터를 찾을 수 없습니다. id=" + chatHistoryId);
  }
}
