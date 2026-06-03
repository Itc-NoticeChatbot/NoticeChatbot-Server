package com.inhatc.noticebot.chat.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.inhatc.noticebot.common.config.GeminiProperties;
import com.inhatc.noticebot.domain.chat.exception.AiResponseFailedException;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

class GeminiClientImplTest {

  private MockWebServer mockWebServer;
  private GeminiClientImpl geminiClient;

  @BeforeEach
  void setUp() throws Exception {
    mockWebServer = new MockWebServer();
    mockWebServer.start();

    String baseUrl = mockWebServer.url("/").toString().replaceAll("/$", "");
    GeminiProperties properties = new GeminiProperties("test-key", baseUrl, 5);

    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(5_000);
    factory.setReadTimeout(5_000);

    RestClient restClient = RestClient.builder().baseUrl(baseUrl).requestFactory(factory).build();

    geminiClient = new GeminiClientImpl(restClient, properties);
  }

  @AfterEach
  void tearDown() throws Exception {
    mockWebServer.shutdown();
  }

  @Test
  void ask_returnsText_whenGeminiRespondsSuccessfully() {
    String responseBody =
        """
        {
          "candidates": [
            {
              "content": {
                "parts": [{ "text": "수강신청은 3월에 진행됩니다." }]
              }
            }
          ]
        }
        """;

    mockWebServer.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .setBody(responseBody));

    String result = geminiClient.ask("수강신청은 언제인가요?", List.of("공지 내용입니다."));

    assertThat(result).isEqualTo("수강신청은 3월에 진행됩니다.");
  }

  @Test
  void ask_throwsException_whenCandidatesIsEmpty() {
    String responseBody = """
        { "candidates": [] }
        """;

    mockWebServer.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .setBody(responseBody));

    assertThatThrownBy(() -> geminiClient.ask("질문", List.of()))
        .isInstanceOf(AiResponseFailedException.class);
  }

  @Test
  void ask_throwsException_whenHttpErrorOccurs() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(500));

    assertThatThrownBy(() -> geminiClient.ask("질문", List.of()))
        .isInstanceOf(AiResponseFailedException.class);
  }

  @Test
  void ask_throwsException_whenRateLimitExceeded() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(429));

    assertThatThrownBy(() -> geminiClient.ask("질문", List.of()))
        .isInstanceOf(AiResponseFailedException.class);
  }
}
