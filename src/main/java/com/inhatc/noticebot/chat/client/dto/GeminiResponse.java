package com.inhatc.noticebot.chat.client.dto;

import java.util.List;

public record GeminiResponse(List<Candidate> candidates) {

  public record Candidate(Content content) {}

  public record Content(List<Part> parts) {}

  public record Part(String text) {}

  public String getText() {
    if (candidates == null || candidates.isEmpty()) return "";
    Candidate candidate = candidates.get(0);
    if (candidate.content() == null) return "";
    List<Part> parts = candidate.content().parts();
    if (parts == null || parts.isEmpty()) return "";
    String text = parts.get(0).text();
    return text != null ? text : "";
  }
}
