package com.inhatc.noticebot.chat.dto;

import com.inhatc.noticebot.domain.chat.ChatHistory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record ChatHistoryResponse(
    Long id, String question, String answer, List<Long> relatedNoticeIds, LocalDateTime createdAt) {

  public static ChatHistoryResponse convert(ChatHistory chatHistory) {
    return new ChatHistoryResponse(
        chatHistory.getId(),
        chatHistory.getQuestion(),
        chatHistory.getAnswer(),
        parseRelatedNoticeIds(chatHistory.getReferencesJson()),
        chatHistory.getCreatedAt());
  }

  private static List<Long> parseRelatedNoticeIds(String referencesJson) {
    if (referencesJson == null || referencesJson.isBlank() || "[]".equals(referencesJson.trim())) {
      return Collections.emptyList();
    }

    String normalized = referencesJson.trim();
    if (normalized.startsWith("[")) {
      normalized = normalized.substring(1);
    }
    if (normalized.endsWith("]")) {
      normalized = normalized.substring(0, normalized.length() - 1);
    }
    if (normalized.isBlank()) {
      return Collections.emptyList();
    }

    return Arrays.stream(normalized.split(","))
        .map(String::trim)
        .filter(token -> !token.isBlank())
        .map(ChatHistoryResponse::parseRelatedNoticeId)
        .filter(Objects::nonNull)
        .toList();
  }

  private static Long parseRelatedNoticeId(String token) {
    try {
      return Long.valueOf(token);
    } catch (NumberFormatException ex) {
      return null;
    }
  }
}
