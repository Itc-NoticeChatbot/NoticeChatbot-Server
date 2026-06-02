# P-02 Notice 엔티티 스키마 확정

## 논리 스키마
- `id`: PK, bigint, auto increment
- `source_site`: varchar(50), not null
- `source_url`: varchar(500), not null
- `title`: varchar(300), not null
- `category`: varchar(100), null
- `content`: longtext, not null
- `published_at`: datetime, null
- `crawled_at`: datetime, not null
- `updated_at`: datetime, not null

## DDL (v1)
```sql
CREATE TABLE notices (
  id BIGINT NOT NULL AUTO_INCREMENT,
  source_site VARCHAR(50) NOT NULL,
  source_url VARCHAR(500) NOT NULL,
  title VARCHAR(300) NOT NULL,
  category VARCHAR(100) NULL,
  content LONGTEXT NOT NULL,
  published_at DATETIME NULL,
  crawled_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_notices_source_url (source_url),
  KEY idx_notices_published_at (published_at),
  KEY idx_notices_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

## URL 중복 처리 기준
- 기준키: `source_url` (정규화 후 비교)
- 정규화 규칙:
1. 앞/뒤 공백 제거
2. fragment(`#...`) 제거
3. `http://` -> `https://` 통일
4. 불필요 추적 파라미터(`utm_*`) 제거

## 중복 발견 시 정책
- 기존 데이터와 제목/본문 동일: `skip`
- 제목/본문 변경: `update` + `updated_at` 갱신
