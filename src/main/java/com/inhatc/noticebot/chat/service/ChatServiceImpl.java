package com.inhatc.noticebot.chat.service;

import com.inhatc.noticebot.chat.client.GeminiClient;
import com.inhatc.noticebot.chat.dto.AskChatRequest;
import com.inhatc.noticebot.chat.dto.ChatAnswerResponse;
import com.inhatc.noticebot.domain.chat.ChatHistory;
import com.inhatc.noticebot.domain.chat.exception.AiResponseFailedException;
import com.inhatc.noticebot.domain.notice.Notice;
import com.inhatc.noticebot.repository.ChatHistoryRepository;
import com.inhatc.noticebot.repository.NoticeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatServiceImpl implements ChatService {

  private final NoticeRepository noticeRepository;
  private final ChatHistoryRepository chatHistoryRepository;
  private final GeminiClient geminiClient;

  public ChatServiceImpl(
      NoticeRepository noticeRepository,
      ChatHistoryRepository chatHistoryRepository,
      GeminiClient geminiClient) {
    this.noticeRepository = noticeRepository;
    this.chatHistoryRepository = chatHistoryRepository;
    this.geminiClient = geminiClient;
  }

  @Override
  @Transactional
  public ChatAnswerResponse ask(AskChatRequest request) {
    String question = request.question();

    List<Notice> relatedNotices =
        noticeRepository.findByTitleContainingOrContentContaining(question, question);

    List<String> noticeContents = relatedNotices.stream().map(Notice::getContent).toList();
    List<Long> relatedNoticeIds = relatedNotices.stream().map(Notice::getId).toList();

    String answer = generateAnswer(question, noticeContents);

    chatHistoryRepository.save(
        new ChatHistory(question, answer, convertToJson(relatedNoticeIds), null));

    return new ChatAnswerResponse(answer, relatedNoticeIds);
  }

  private String generateAnswer(String question, List<String> noticeContents) {
    try {
      return geminiClient.ask(question, noticeContents);
    } catch (Exception ex) {
      throw new AiResponseFailedException();
    }
  }

  private String convertToJson(List<Long> ids) {
    if (ids.isEmpty()) return "[]";
    return "[" + ids.stream().map(String::valueOf).reduce((a, b) -> a + "," + b).orElse("") + "]";
  }
}
