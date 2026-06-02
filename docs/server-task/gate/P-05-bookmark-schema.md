# P-05 Bookmark 스키마 반영 메모

## 배경
- `Bookmark` 엔티티는 `notice_id + question` 조합을 유니크 키로 사용한다.
- `local` 프로필은 `ddl-auto=update`로 동작하지만, `dev`/`prod` 프로필은 `ddl-auto=validate`이므로 스키마가 자동 변경되지 않는다.
- 따라서 배포 전에는 아래 DDL이 DB에 반영되어 있어야 한다.

## 적용 대상
- 테이블: `bookmarks`
- 목적:
  - 동일 공지에 동일 질문이 중복 저장되는 것을 DB 레벨에서 차단
  - 북마크 삭제/조회 성능을 위한 기존 `notice_id` 인덱스 유지

## DDL
```sql
ALTER TABLE bookmarks
  ADD CONSTRAINT uq_bookmarks_notice_question
  UNIQUE KEY (notice_id, question);
```

## 적용 확인
```sql
SHOW CREATE TABLE bookmarks;
```

확인 결과에 아래 제약이 포함되어 있어야 한다.
- `UNIQUE KEY uq_bookmarks_notice_question (notice_id, question)`

## 운영 메모
- 애플리케이션 레벨에서도 중복 체크를 수행하지만, 동시 요청 경쟁 상황에서는 DB 유니크 제약이 최종 안전장치다.
- 유니크 제약 위반 시 서버는 `BOOKMARK_DUPLICATED`(`409`)로 응답해야 한다.
