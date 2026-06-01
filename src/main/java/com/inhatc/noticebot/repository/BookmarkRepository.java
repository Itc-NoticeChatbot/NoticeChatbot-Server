package com.inhatc.noticebot.repository;

import com.inhatc.noticebot.domain.bookmark.Bookmark;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	List<Bookmark> findByNotice_Id(Long noticeId);
}
