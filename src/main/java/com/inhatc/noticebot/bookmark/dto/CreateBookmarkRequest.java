package com.inhatc.noticebot.bookmark.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateBookmarkRequest(
    @NotNull(message = "공지 ID를 입력해주세요.") @Positive(message = "공지 ID는 1 이상이어야 합니다.")
        Long noticeId,
    @NotBlank(message = "질문을 입력해주세요.")
        @Size(max = 200, message = "질문은 200자 이하로 입력해주세요.")
        String question) {}
