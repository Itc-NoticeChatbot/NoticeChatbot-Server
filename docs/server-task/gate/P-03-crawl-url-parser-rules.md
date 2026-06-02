# P-03 크롤링 대상 URL + 파싱 규칙 확정

## 수집 대상(1차)
- 대학 대표 공지 영역
- 학사/일반 공지성 게시물

## URL 규칙
- 게시물 URL 패턴(확정):
  - `/bbs/kr/11/{articleId}/artclView.do`
- 수집 대상 판별 정규식:
  - `^https://www\.inhatc\.ac\.kr/bbs/kr/11/\d+/artclView\.do.*$`

## 시드 페이지(탐색 시작점)
- `https://www.inhatc.ac.kr/kr/460/subview.do`
- 위 페이지에서 게시물 링크를 추출해 상세 URL 패턴 매칭 후 수집

## 파싱 필드 규칙
- `title`: 상세 페이지 제목 영역 텍스트
- `published_at`: 작성일 필드 텍스트 -> `yyyy-MM-dd` 또는 `yyyy.MM.dd.` 파싱
- `source_url`: 상세 페이지 절대 URL
- `category`: 게시판/분류 라벨(없으면 null)
- `content`: 본문 컨테이너 텍스트(HTML 태그 제거)

## 파싱 우선순위
1. 구조화된 메타(작성일/제목)
2. 본문 컨테이너
3. 실패 시 에러 로그 + 스킵

## 타임아웃/재시도
- connect timeout: 5s
- read timeout: 8s
- 재시도: 최대 2회 (지수 백오프)
