package com.inhatc.noticebot.chat.service;

import com.inhatc.noticebot.chat.dto.AskChatRequest;
import com.inhatc.noticebot.chat.dto.ChatAnswerResponse;

public interface ChatService {

  ChatAnswerResponse ask(AskChatRequest request);
}
