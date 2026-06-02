package com.inhatc.noticebot.chat.client;

import java.util.List;

public interface GeminiClient {

  String ask(String question, List<String> noticeContents);
}
