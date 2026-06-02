package com.inhatc.noticebot.bookmark.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inhatc.noticebot.bookmark.dto.BookmarkResponse;
import com.inhatc.noticebot.bookmark.dto.CreateBookmarkRequest;
import com.inhatc.noticebot.domain.bookmark.Bookmark;
import com.inhatc.noticebot.domain.bookmark.exception.BookmarkDuplicatedException;
import com.inhatc.noticebot.domain.bookmark.exception.BookmarkNotFoundException;
import com.inhatc.noticebot.domain.notice.Notice;
import com.inhatc.noticebot.domain.notice.exception.NoticeNotFoundException;
import com.inhatc.noticebot.repository.BookmarkRepository;
import com.inhatc.noticebot.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceImplTest {

  @Mock private BookmarkRepository bookmarkRepository;

  @Mock private NoticeRepository noticeRepository;

  @InjectMocks private BookmarkServiceImpl bookmarkService;

  @Captor private ArgumentCaptor<Bookmark> bookmarkCaptor;

  private Notice notice;

  @BeforeEach
  void setUp() {
    notice =
        new Notice(
            "inhatc",
            "https://example.com/notices/1",
            "장학금 안내",
            "학사",
            "상세 내용",
            LocalDateTime.of(2026, 6, 1, 9, 0),
            LocalDateTime.of(2026, 6, 1, 9, 0),
            LocalDateTime.of(2026, 6, 1, 9, 0));
    ReflectionTestUtils.setField(notice, "id", 1L);
  }

  @Test
  void create_savesBookmark_whenRequestIsValid() {
    CreateBookmarkRequest request = new CreateBookmarkRequest(1L, "장학금 신청 기간이 언제야?");

    when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));
    when(bookmarkRepository.existsByNotice_IdAndQuestion(1L, request.question())).thenReturn(false);
    when(bookmarkRepository.saveAndFlush(any(Bookmark.class)))
        .thenAnswer(
            invocation -> {
              Bookmark bookmark = invocation.getArgument(0);
              ReflectionTestUtils.setField(bookmark, "id", 10L);
              return bookmark;
            });

    BookmarkResponse response = bookmarkService.create(request);

    verify(bookmarkRepository).saveAndFlush(bookmarkCaptor.capture());
    Bookmark savedBookmark = bookmarkCaptor.getValue();
    assertThat(savedBookmark.getNotice()).isSameAs(notice);
    assertThat(savedBookmark.getQuestion()).isEqualTo(request.question());
    assertThat(response.id()).isEqualTo(10L);
    assertThat(response.noticeId()).isEqualTo(1L);
    assertThat(response.noticeTitle()).isEqualTo("장학금 안내");
  }

  @Test
  void create_throwsException_whenNoticeDoesNotExist() {
    CreateBookmarkRequest request = new CreateBookmarkRequest(99L, "존재하지 않는 공지");

    when(noticeRepository.findById(99L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookmarkService.create(request)).isInstanceOf(NoticeNotFoundException.class);
    verify(bookmarkRepository, never()).save(any(Bookmark.class));
  }

  @Test
  void create_throwsException_whenBookmarkIsDuplicated() {
    CreateBookmarkRequest request = new CreateBookmarkRequest(1L, "중복 질문");

    when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));
    when(bookmarkRepository.existsByNotice_IdAndQuestion(1L, request.question())).thenReturn(true);

    assertThatThrownBy(() -> bookmarkService.create(request))
        .isInstanceOf(BookmarkDuplicatedException.class);
    verify(bookmarkRepository, never()).save(any(Bookmark.class));
  }

  @Test
  void create_throwsException_whenUniqueConstraintFailsDuringSave() {
    CreateBookmarkRequest request = new CreateBookmarkRequest(1L, "동시 요청 질문");

    when(noticeRepository.findById(1L)).thenReturn(Optional.of(notice));
    when(bookmarkRepository.existsByNotice_IdAndQuestion(1L, request.question())).thenReturn(false);
    when(bookmarkRepository.saveAndFlush(any(Bookmark.class)))
        .thenThrow(new DataIntegrityViolationException("duplicate key"));

    assertThatThrownBy(() -> bookmarkService.create(request))
        .isInstanceOf(BookmarkDuplicatedException.class);
  }

  @Test
  void getList_returnsBookmarksOrderedByCreatedAtDesc() {
    Bookmark newerBookmark = new Bookmark(notice, "최근 질문", LocalDateTime.of(2026, 6, 2, 10, 0));
    ReflectionTestUtils.setField(newerBookmark, "id", 2L);

    Bookmark olderBookmark = new Bookmark(notice, "이전 질문", LocalDateTime.of(2026, 6, 1, 10, 0));
    ReflectionTestUtils.setField(olderBookmark, "id", 1L);

    when(bookmarkRepository.findAll(any(Sort.class))).thenReturn(List.of(newerBookmark, olderBookmark));

    List<BookmarkResponse> response = bookmarkService.getList();

    verify(bookmarkRepository).findAll(eq(Sort.by(Sort.Direction.DESC, "createdAt")));
    assertThat(response).hasSize(2);
    assertThat(response.get(0).id()).isEqualTo(2L);
    assertThat(response.get(1).id()).isEqualTo(1L);
  }

  @Test
  void delete_throwsException_whenBookmarkDoesNotExist() {
    when(bookmarkRepository.findById(5L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> bookmarkService.delete(5L)).isInstanceOf(BookmarkNotFoundException.class);
    verify(bookmarkRepository, never()).delete(any(Bookmark.class));
  }
}
