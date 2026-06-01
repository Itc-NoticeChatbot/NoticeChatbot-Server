package com.inhatc.noticebot.domain.bookmark;

import com.inhatc.noticebot.domain.notice.Notice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(
		name = "bookmarks",
		indexes = {@Index(name = "idx_bookmarks_notice_id", columnList = "notice_id")}
)
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "notice_id", nullable = false)
	private Notice notice;

	@Column(nullable = false, length = 200)
	private String question;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	protected Bookmark() {
	}

	public Bookmark(Notice notice, String question, LocalDateTime createdAt) {
		this.notice = notice;
		this.question = question;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	public void onCreate() {
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
		}
	}
}
