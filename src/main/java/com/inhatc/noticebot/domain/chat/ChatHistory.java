package com.inhatc.noticebot.domain.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(
		name = "chat_histories",
		indexes = {@Index(name = "idx_chat_histories_created_at", columnList = "created_at")}
)
public class ChatHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 1000)
	private String question;

	@Lob
	@Column(nullable = false)
	private String answer;

	@Lob
	@Column(name = "references_json")
	private String referencesJson;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	protected ChatHistory() {
	}

	public ChatHistory(String question, String answer, String referencesJson, LocalDateTime createdAt) {
		this.question = question;
		this.answer = answer;
		this.referencesJson = referencesJson;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getReferencesJson() {
		return referencesJson;
	}

	public void setReferencesJson(String referencesJson) {
		this.referencesJson = referencesJson;
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
