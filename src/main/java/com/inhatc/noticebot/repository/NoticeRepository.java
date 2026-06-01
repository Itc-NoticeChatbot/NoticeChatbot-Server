package com.inhatc.noticebot.repository;

import com.inhatc.noticebot.domain.notice.Notice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

	Optional<Notice> findBySourceUrl(String sourceUrl);

	boolean existsBySourceUrl(String sourceUrl);
}
