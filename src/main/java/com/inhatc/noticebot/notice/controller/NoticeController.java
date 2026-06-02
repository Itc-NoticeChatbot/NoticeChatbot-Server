package com.inhatc.noticebot.notice.controller;

import com.inhatc.noticebot.common.response.ApiResponse;
import com.inhatc.noticebot.notice.dto.CrawlResultResponse;
import com.inhatc.noticebot.notice.dto.NoticeResponse;
import com.inhatc.noticebot.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notice", description = "공지 크롤링 및 조회 API")
@RestController
@RequestMapping("/api/notices")
public class NoticeController {

  private final NoticeService noticeService;

  public NoticeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @Operation(summary = "공지 크롤링 실행", description = "인하공전 공지사항을 크롤링하여 DB에 저장/업데이트합니다.")
  @PostMapping("/crawl")
  public ResponseEntity<ApiResponse<CrawlResultResponse>> crawl() {
    CrawlResultResponse result = noticeService.crawlAndSave();
    return ResponseEntity.ok(ApiResponse.ok(result, "크롤링이 완료되었습니다."));
  }

  @Operation(summary = "공지 목록 조회", description = "저장된 공지 목록 전체를 반환합니다.")
  @GetMapping
  public ResponseEntity<ApiResponse<List<NoticeResponse>>> getList() {
    List<NoticeResponse> list = noticeService.getList();
    return ResponseEntity.ok(ApiResponse.ok(list));
  }

  @Operation(summary = "공지 단건 조회", description = "ID로 특정 공지를 조회합니다.")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<NoticeResponse>> get(@PathVariable Long id) {
    NoticeResponse response = noticeService.get(id);
    return ResponseEntity.ok(ApiResponse.ok(response));
  }
}
