package com.inhatc.noticebot.chat.dto;

import java.util.List;

public record ChatAnswerResponse(String answer, List<Long> relatedNoticeIds) {}
