package com.inhatc.noticebot.notice.service;

import com.inhatc.noticebot.notice.dto.CrawlResultResponse;
import com.inhatc.noticebot.notice.dto.NoticeResponse;
import java.util.List;

public interface NoticeService {

  CrawlResultResponse crawlAndSave();

  List<NoticeResponse> getList();

  NoticeResponse get(Long id);
}
