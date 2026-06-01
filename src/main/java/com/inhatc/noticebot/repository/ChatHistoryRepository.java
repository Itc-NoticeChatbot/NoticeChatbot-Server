package com.inhatc.noticebot.repository;

import com.inhatc.noticebot.domain.chat.ChatHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

  List<ChatHistory> findTop20ByOrderByCreatedAtDesc();
}
