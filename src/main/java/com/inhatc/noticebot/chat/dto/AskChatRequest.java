package com.inhatc.noticebot.chat.dto;

import jakarta.validation.constraints.NotBlank;

public record AskChatRequest(@NotBlank(message = "질문을 입력해주세요.") String question) {}
