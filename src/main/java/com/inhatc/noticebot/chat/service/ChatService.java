package com.inhatc.noticebot.chat.service;

import com.inhatc.noticebot.chat.dto.AskChatRequest;
import com.inhatc.noticebot.chat.dto.ChatAnswerResponse;
import com.inhatc.noticebot.chat.dto.ChatHistoryResponse;
import java.util.List;

public interface ChatService {

  ChatAnswerResponse ask(AskChatRequest request);

  List<ChatHistoryResponse> getHistories();

  void deleteHistory(Long id);
}
