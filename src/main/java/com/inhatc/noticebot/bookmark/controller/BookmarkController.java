package com.inhatc.noticebot.bookmark.controller;

import com.inhatc.noticebot.bookmark.dto.BookmarkResponse;
import com.inhatc.noticebot.bookmark.dto.CreateBookmarkRequest;
import com.inhatc.noticebot.bookmark.service.BookmarkService;
import com.inhatc.noticebot.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bookmark", description = "북마크 저장 및 관리 API")
@Validated
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

  private final BookmarkService bookmarkService;

  public BookmarkController(BookmarkService bookmarkService) {
    this.bookmarkService = bookmarkService;
  }

  @Operation(summary = "북마크 저장", description = "공지와 질문 조합을 북마크로 저장합니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<BookmarkResponse>> create(
      @RequestBody @Valid CreateBookmarkRequest request) {
    BookmarkResponse response = bookmarkService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(response));
  }

  @Operation(summary = "북마크 목록 조회", description = "저장된 북마크 목록을 최신순으로 조회합니다.")
  @GetMapping
  public ResponseEntity<ApiResponse<List<BookmarkResponse>>> getList() {
    List<BookmarkResponse> response = bookmarkService.getList();
    return ResponseEntity.ok(ApiResponse.ok(response));
  }

  @Operation(summary = "북마크 삭제", description = "북마크 ID로 저장된 북마크를 삭제합니다.")
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(
      @PathVariable @Positive(message = "북마크 ID는 1 이상이어야 합니다.") Long id) {
    bookmarkService.delete(id);
    return ResponseEntity.ok(ApiResponse.ok(null, "deleted"));
  }
}
