package com.inhatc.noticebot.chat.controller;

import com.inhatc.noticebot.chat.dto.AskChatRequest;
import com.inhatc.noticebot.chat.dto.ChatAnswerResponse;
import com.inhatc.noticebot.chat.dto.ChatHistoryResponse;
import com.inhatc.noticebot.chat.service.ChatService;
import com.inhatc.noticebot.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Chat", description = "챗봇 질문 처리 API")
@Validated
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

  @Operation(summary = "대화 이력 목록 조회", description = "최근 대화 이력 목록을 최신순으로 반환합니다.")
  @GetMapping("/histories")
  public ResponseEntity<ApiResponse<List<ChatHistoryResponse>>> getHistories() {
    List<ChatHistoryResponse> response = chatService.getHistories();
    return ResponseEntity.ok(ApiResponse.ok(response));
  }

  @Operation(summary = "대화 이력 삭제", description = "대화 이력 ID로 특정 대화 이력을 삭제합니다.")
  @DeleteMapping("/histories/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteHistory(
      @PathVariable @Positive(message = "대화 이력 ID는 1 이상이어야 합니다.") Long id) {
    chatService.deleteHistory(id);
    return ResponseEntity.ok(ApiResponse.ok(null, "deleted"));
  }
}
