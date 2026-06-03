package com.inhatc.noticebot.chat.client;

import java.util.List;

public class PromptBuilder {

  private static final String TEMPLATE =
      "아래 공지 목록을 참고해 질문에 답하세요. 마크다운 문법(**, *, #, - 등)을 사용하지 말고 일반 텍스트로만 답변하세요.\n%s\n질문: %s";

  private static final String FALLBACK =
      "관련 공지를 찾을 수 없습니다. 일반적인 내용으로 답변해주세요. 마크다운 문법(**, *, #, - 등)을 사용하지 말고 일반 텍스트로만 답변하세요.\n질문: %s";

  private PromptBuilder() {}

  public static String create(String question, List<String> noticeContents) {
    if (noticeContents.isEmpty()) {
      return String.format(FALLBACK, question);
    }
    String noticeBlock = convertToNoticeBlock(noticeContents);
    return String.format(TEMPLATE, noticeBlock, question);
  }

  private static String convertToNoticeBlock(List<String> contents) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < contents.size(); i++) {
      builder.append(String.format("[공지%d] %s", i + 1, contents.get(i)));
      if (i < contents.size() - 1) {
        builder.append("\n");
      }
    }
    return builder.toString();
  }
}
