package com.inhatc.noticebot.chat.controller;

import com.inhatc.noticebot.chat.dto.AskChatRequest;
import com.inhatc.noticebot.chat.dto.ChatAnswerResponse;
import com.inhatc.noticebot.chat.service.ChatService;
import com.inhatc.noticebot.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Chat", description = "챗봇 질문 처리 API")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @Operation(summary = "챗봇 질문", description = "질문을 입력하면 관련 공지 기반 응답을 반환합니다.")
  @PostMapping("/ask")
  public ResponseEntity<ApiResponse<ChatAnswerResponse>> ask(
      @RequestBody @Valid AskChatRequest request) {
    ChatAnswerResponse response = chatService.ask(request);
    return ResponseEntity.ok(ApiResponse.ok(response));
  }
}
