package com.inhatc.noticebot.chat.client.dto;

import java.util.List;

public record GeminiRequest(List<Content> contents) {

  public record Content(List<Part> parts) {}

  public record Part(String text) {}

  public static GeminiRequest create(String text) {
    return new GeminiRequest(List.of(new Content(List.of(new Part(text)))));
  }
}
