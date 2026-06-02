package com.inhatc.noticebot.bookmark.service;

import com.inhatc.noticebot.bookmark.dto.BookmarkResponse;
import com.inhatc.noticebot.bookmark.dto.CreateBookmarkRequest;
import com.inhatc.noticebot.domain.bookmark.Bookmark;
import com.inhatc.noticebot.domain.bookmark.exception.BookmarkDuplicatedException;
import com.inhatc.noticebot.domain.bookmark.exception.BookmarkNotFoundException;
import com.inhatc.noticebot.domain.notice.Notice;
import com.inhatc.noticebot.domain.notice.exception.NoticeNotFoundException;
import com.inhatc.noticebot.repository.BookmarkRepository;
import com.inhatc.noticebot.repository.NoticeRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

  private final BookmarkRepository bookmarkRepository;
  private final NoticeRepository noticeRepository;

  public BookmarkServiceImpl(BookmarkRepository bookmarkRepository, NoticeRepository noticeRepository) {
    this.bookmarkRepository = bookmarkRepository;
    this.noticeRepository = noticeRepository;
  }

  @Override
  @Transactional
  public BookmarkResponse create(CreateBookmarkRequest request) {
    Notice notice =
        noticeRepository
            .findById(request.noticeId())
            .orElseThrow(() -> new NoticeNotFoundException(request.noticeId()));

    if (bookmarkRepository.existsByNotice_IdAndQuestion(request.noticeId(), request.question())) {
      throw new BookmarkDuplicatedException(request.noticeId(), request.question());
    }

    Bookmark bookmark = new Bookmark(notice, request.question(), null);
    Bookmark savedBookmark = bookmarkRepository.save(bookmark);
    return BookmarkResponse.convert(savedBookmark);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookmarkResponse> getList() {
    return bookmarkRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
        .map(BookmarkResponse::convert)
        .toList();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Bookmark bookmark =
        bookmarkRepository.findById(id).orElseThrow(() -> new BookmarkNotFoundException(id));
    bookmarkRepository.delete(bookmark);
  }
}
