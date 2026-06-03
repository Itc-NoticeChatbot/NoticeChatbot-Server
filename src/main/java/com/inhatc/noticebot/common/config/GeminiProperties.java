package com.inhatc.noticebot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "gemini.api")
public record GeminiProperties(
    String key, String baseUrl, @DefaultValue("30") int timeoutSeconds) {}
