package com.inhatc.noticebot.domain.notice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "notices",
    uniqueConstraints = {
      @UniqueConstraint(name = "uq_notices_source_url", columnNames = "source_url")
    },
    indexes = {
      @Index(name = "idx_notices_published_at", columnList = "published_at"),
      @Index(name = "idx_notices_category", columnList = "category")
    })
public class Notice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "source_site", nullable = false, length = 50)
  private String sourceSite;

  @Column(name = "source_url", nullable = false, length = 500)
  private String sourceUrl;

  @Column(nullable = false, length = 300)
  private String title;

  @Column(length = 100)
  private String category;

  @Lob
  @Column(nullable = false)
  private String content;

  @Column(name = "published_at")
  private LocalDateTime publishedAt;

  @Column(name = "crawled_at", nullable = false)
  private LocalDateTime crawledAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  protected Notice() {}

  public Notice(
      String sourceSite,
      String sourceUrl,
      String title,
      String category,
      String content,
      LocalDateTime publishedAt,
      LocalDateTime crawledAt,
      LocalDateTime updatedAt) {
    this.sourceSite = sourceSite;
    this.sourceUrl = sourceUrl;
    this.title = title;
    this.category = category;
    this.content = content;
    this.publishedAt = publishedAt;
    this.crawledAt = crawledAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public String getSourceSite() {
    return sourceSite;
  }

  public void setSourceSite(String sourceSite) {
    this.sourceSite = sourceSite;
  }

  public String getSourceUrl() {
    return sourceUrl;
  }

  public void setSourceUrl(String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(LocalDateTime publishedAt) {
    this.publishedAt = publishedAt;
  }

  public LocalDateTime getCrawledAt() {
    return crawledAt;
  }

  public void setCrawledAt(LocalDateTime crawledAt) {
    this.crawledAt = crawledAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PrePersist
  public void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    if (this.crawledAt == null) {
      this.crawledAt = now;
    }
    if (this.updatedAt == null) {
      this.updatedAt = now;
    }
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
