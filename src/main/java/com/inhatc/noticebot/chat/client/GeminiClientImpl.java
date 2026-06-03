package com.inhatc.noticebot.chat.client;

import com.inhatc.noticebot.chat.client.dto.GeminiRequest;
import com.inhatc.noticebot.chat.client.dto.GeminiResponse;
import com.inhatc.noticebot.common.config.GeminiProperties;
import com.inhatc.noticebot.domain.chat.exception.AiResponseFailedException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Primary
@Component
public class GeminiClientImpl implements GeminiClient {

  private static final Logger log = LoggerFactory.getLogger(GeminiClientImpl.class);
  private static final String API_PATH = "/v1beta/models/gemini-2.5-flash:generateContent";

  private final RestClient restClient;
  private final GeminiProperties properties;

  public GeminiClientImpl(RestClient geminiRestClient, GeminiProperties properties) {
    this.restClient = geminiRestClient;
    this.properties = properties;
  }

  @Override
  public String ask(String question, List<String> noticeContents) {
    String prompt = PromptBuilder.create(question, noticeContents);
    GeminiRequest request = GeminiRequest.create(prompt);

    try {
      GeminiResponse response =
          restClient
              .post()
              .uri(API_PATH + "?key={key}", properties.key())
              .contentType(MediaType.APPLICATION_JSON)
              .body(request)
              .retrieve()
              .body(GeminiResponse.class);

      if (response == null) {
        throw new AiResponseFailedException();
      }

      String text = response.getText();
      if (text.isEmpty()) {
        throw new AiResponseFailedException();
      }

      return text;
    } catch (AiResponseFailedException ex) {
      throw ex;
    } catch (RestClientException ex) {
      log.error("Gemini API 호출 실패: {}", ex.getMessage());
      throw new AiResponseFailedException();
    }
  }
}
