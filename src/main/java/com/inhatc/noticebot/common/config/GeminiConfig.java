package com.inhatc.noticebot.common.config;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class GeminiConfig {

  @Bean
  public RestClient geminiRestClient(GeminiProperties properties) {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout((int) Duration.ofSeconds(properties.timeoutSeconds()).toMillis());
    factory.setReadTimeout((int) Duration.ofSeconds(properties.timeoutSeconds()).toMillis());

    return RestClient.builder().baseUrl(properties.baseUrl()).requestFactory(factory).build();
  }
}
