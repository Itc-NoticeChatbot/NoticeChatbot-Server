package com.inhatc.noticebot.chat.client;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StubGeminiClient implements GeminiClient {

  @Override
  public String ask(String question, List<String> noticeContents) {
    return "Gemini 연동 준비 중입니다.";
  }
}
