package com.inhatc.noticebot.bookmark.service;

import com.inhatc.noticebot.bookmark.dto.BookmarkResponse;
import com.inhatc.noticebot.bookmark.dto.CreateBookmarkRequest;
import java.util.List;

public interface BookmarkService {

  BookmarkResponse create(CreateBookmarkRequest request);

  List<BookmarkResponse> getList();

  void delete(Long id);
}
