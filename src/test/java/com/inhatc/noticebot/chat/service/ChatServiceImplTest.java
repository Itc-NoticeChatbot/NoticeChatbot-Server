package com.inhatc.noticebot.chat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inhatc.noticebot.chat.client.GeminiClient;
import com.inhatc.noticebot.chat.dto.ChatHistoryResponse;
import com.inhatc.noticebot.domain.chat.ChatHistory;
import com.inhatc.noticebot.domain.chat.exception.ChatHistoryNotFoundException;
import com.inhatc.noticebot.repository.ChatHistoryRepository;
import com.inhatc.noticebot.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

  @Mock private NoticeRepository noticeRepository;

  @Mock private ChatHistoryRepository chatHistoryRepository;

  @Mock private GeminiClient geminiClient;

  @Mock private TransactionTemplate transactionTemplate;

  @InjectMocks private ChatServiceImpl chatService;

  @Test
  void getHistories_returnsRecentHistoriesWithRelatedNoticeIds() {
    ChatHistory recentHistory =
        new ChatHistory(
            "최근 질문", "최근 답변", "[1, 2, 3]", LocalDateTime.of(2026, 6, 2, 12, 0));
    ReflectionTestUtils.setField(recentHistory, "id", 2L);

    ChatHistory olderHistory =
        new ChatHistory("이전 질문", "이전 답변", "[]", LocalDateTime.of(2026, 6, 1, 12, 0));
    ReflectionTestUtils.setField(olderHistory, "id", 1L);

    when(chatHistoryRepository.findAll(any(Sort.class))).thenReturn(List.of(recentHistory, olderHistory));

    List<ChatHistoryResponse> response = chatService.getHistories();

    verify(chatHistoryRepository).findAll(eq(Sort.by(Sort.Direction.DESC, "createdAt")));
    assertThat(response).hasSize(2);
    assertThat(response.get(0).id()).isEqualTo(2L);
    assertThat(response.get(0).relatedNoticeIds()).containsExactly(1L, 2L, 3L);
    assertThat(response.get(1).relatedNoticeIds()).isEmpty();
  }

  @Test
  void deleteHistory_deletesHistory_whenItExists() {
    ChatHistory chatHistory =
        new ChatHistory("질문", "답변", "[1]", LocalDateTime.of(2026, 6, 2, 12, 0));
    ReflectionTestUtils.setField(chatHistory, "id", 3L);

    when(chatHistoryRepository.findById(3L)).thenReturn(Optional.of(chatHistory));

    chatService.deleteHistory(3L);

    verify(chatHistoryRepository).delete(chatHistory);
  }

  @Test
  void deleteHistory_throwsException_whenHistoryDoesNotExist() {
    when(chatHistoryRepository.findById(99L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> chatService.deleteHistory(99L))
        .isInstanceOf(ChatHistoryNotFoundException.class);
    verify(chatHistoryRepository, never()).delete(any(ChatHistory.class));
  }
}
