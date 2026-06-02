# 서버 Task 타임라인 (압축 플랜, D-3 완료 기준)

## 마감 기준
- 공식 마감: 2026-06-04 08:00
- 내부 완료 목표: 2026-06-03 23:00 (실질 마감)
- 현재 브랜치: `develop`
- 브랜치 게이트: 만약에 현재 작업 브런치가 `develop`이면 코드 로직 작성은 금지한다.

## 운영 원칙
1. 기존 초세분화(`S-xx-y`)는 참조용으로 유지한다.
2. 실제 실행은 압축 Task(`C-xx`) 기준으로 진행한다.
3. `develop`에서는 문서/명세/체크리스트만 처리한다.

## 압축 Task 타임라인
| Task ID | 기간 | 작업 묶음 | 주요 산출물 | Depends On | 연계 주차 | Quick Action |
| --- | --- | --- | --- | --- | --- | --- |
| C-01 | 2026-06-02 | MVP 범위 + 완료 기준 최종 고정 | 포함/제외 범위표, 완료 체크리스트 | - | W2 | Task1 기준 포함/제외를 1페이지로 확정 |
| C-02 | 2026-06-02 | 아키텍처/레이어/API 계약 동결 | Controller-Service-Repository 책임표, API 목록 | C-01 | W6 | F1~F4 엔드포인트와 책임 경계를 동시에 확정 |
| C-03 | 2026-06-02 | 도메인/리포지터리 설계 묶음 | Notice/Bookmark/ChatHistory 필드표 + Repo 시그니처 | C-02 | W3,W4 | 엔티티 3종 필드 + 저장/조회 메서드 목록 확정 |
| C-04 | 2026-06-02 | F1 크롤링 정책 패키지 | URL 인벤토리, 파싱 매핑, 중복 정책, 수집 지표 | C-03 | W4,W5 | 크롤링 규칙 4종을 한 문서로 통합 |
| C-05 | 2026-06-02 | F2 질문 처리 규격 패키지 | Ask DTO/Validation/에러코드/검색전략/AI 실패정책 | C-04 | W6,W9 | 질문 API 명세와 Gemini 경계를 한 번에 확정 |
| C-06 | 2026-06-03 | F3+F4 저장 기능 규격 패키지 | Bookmark/ChatHistory 요청응답예시 + 예외/트랜잭션 규칙 | C-05 | W5,W6,W9 | 북마크/대화이력 규격을 묶어서 문서화 |
| C-07 | 2026-06-03 | 보안 기본 설정 설계 | permitAll 기준 Security 초안, 허용/차단 경로 목록 | C-06 | W11 | MVP 보안 정책(현재/확장)을 분리 작성 |
| C-08 | 2026-06-03 | 통합/회귀 테스트 설계 | F1~F4 테스트 매트릭스 + 회귀 체크리스트 | C-07 | W5 | 기능별 정상/예외 케이스를 표로 고정 |
| C-09 | 2026-06-03 | 배포/운영 준비 문서화 | start/stop 체크리스트, Nginx 80->8080 점검표 | C-08 | W13 | 배포 전 점검 항목을 실행 순서로 정렬 |
| C-10 | 2026-06-03 | 최종 리뷰/핸드오프 | 최종 task 상태표, 인수인계 노트 | C-09 | W2~W13 | 누락 항목 확인 후 다음 브랜치 작업 조건 확정 |

## 현재 기준 실행 큐 (develop 최신화 이후)
1. `F1` 공지 크롤링/저장 기능 구현
2. `F2` 질문 처리 API 구현
3. `F3` 북마크 CRUD 구현
4. `F4` 대화 이력 CRUD 구현
5. `C-07` 보안 기본 설정 설계
6. `C-08` 통합/회귀 테스트 설계
7. `C-09` 배포/운영 준비 문서화
8. `C-10` 최종 리뷰/핸드오프

## 최신 상태 요약 (2026-06-02)
- `P-01~P-04`: 문서 게이트 완료
- `I-01~I-04`: 코드 초기세팅 게이트 완료
- `develop`: `origin/develop` 최신 반영 완료 (`#12~#15` merge 반영)
- 현재 판정: `API 구현(F1~F4) 착수 가능`
- 작업 브랜치 원칙: `develop`에서는 직접 코드 로직 작성 금지, 기능별 `feat/.../#issue-number` 브랜치에서 구현

## Notion 정렬 규칙 (스킬 참고용)
- 기본 원칙: `Period` 기준 `내림차순(Descending)`
- 실행 뷰에서는 동일 날짜 내 `Task ID` 오름차순으로 읽어 순서 혼동을 방지한다.

## 2인 작업 분배 (훈진 / 명진)

### 담당 매핑
| Task ID | 담당 | 이유 |
| --- | --- | --- |
| C-01 | 훈진 | 범위/완료 기준 최종 합의 주도 |
| C-02 | 명진 | API/레이어 계약 정리 집중 |
| C-03 | 훈진 | 도메인/리포지터리 기준선 확정 |
| C-04 | 명진 | 크롤링 규칙 패키지 통합 담당 |
| C-05 | 훈진 | 질문/검증/AI 경계 명세 통합 |
| C-06 | 명진 | 저장 기능(F3/F4) 규격 통합 |
| C-07 | 훈진 | 보안 정책(현재/확장) 구조 정리 |
| C-08 | 명진 | 테스트 매트릭스/회귀 설계 집중 |
| C-09 | 명진 | 배포/운영 체크리스트 통합 |
| C-10 | 공동 | 최종 리뷰 및 핸드오프 |

### 실행 순서 (병렬)
1. 6/2 1차 병렬
- 훈진: `C-01` -> `C-03` -> `C-05`
- 명진: `C-02` -> `C-04`

2. 6/3 2차 병렬
- 훈진: `C-07`
- 명진: `C-06` -> `C-08` -> `C-09`

3. 6/3 마무리
- 공동: `C-10` (누락 점검 + 인수인계)

### 노션 분배 뷰
- `Compressed D-3 Assignment`: `view://372f8dfb-2e3c-8109-a9a4-000ce12f397d`
- 필터: `Plan Version = Compressed D-3`
- 그룹: `Owner`

## 구현 전 필수 4개 세팅 Task (Gate)
| Task ID | 작업 | 담당 | Depends On | 산출물 |
| --- | --- | --- | --- | --- |
| P-01 | DB 연결 정보 확정 (`application.yml`/env) | 훈진 | - | DB 접속 설정 문서 + 예시 env 키 |
| P-02 | Notice 엔티티 스키마 확정 (필드/인덱스/URL 중복 기준) | 훈진 | P-01 | Notice 스키마 표 + URL 유니크 기준 |
| P-03 | 크롤링 대상 URL + 파싱 규칙 확정 | 훈진 | P-02 | 대상 URL 목록 + 필드 매핑 규칙 |
| P-04 | 공통 응답/에러 포맷 확정 (`success/errorCode/message`) | 훈진 | P-02 | 공통 응답 JSON 예시 + 에러코드 표 |

### Gate 완료 조건
- `P-01~P-04`가 모두 완료되기 전에는 API 구현(F1) 코드 작업 시작 금지.

## Gate 실행 결과 (완료)
- 완료 일시: 2026-06-01
- 완료 항목: `P-01`, `P-02`, `P-03`, `P-04`
- 산출물:
  - `docs/server-task/gate/P-01-db-connection.md`
  - `docs/server-task/gate/P-02-notice-schema.md`
  - `docs/server-task/gate/P-03-crawl-url-parser-rules.md`
  - `docs/server-task/gate/P-04-response-error-format.md`
- 게이트 판정: `문서 기준 F1 착수 가능`

## 코드 초기세팅 Task (Pre-API Init)
| Task ID | 작업 | 담당 | Depends On | 산출물 |
| --- | --- | --- | --- | --- |
| I-01 | Build/Config Baseline 세팅 | 훈진 | P-01 | `build.gradle.kts` 의존성(web,jpa,validation,mysql), `application.yml` + `local/dev/prod` 프로필, env 키 바인딩 |
| I-02 | DB Baseline + JPA Entity/Repository 세팅 | 훈진 | I-01, P-02 | DDL 기준 반영, `Notice/Bookmark/ChatHistory` 엔티티 + Repository 인터페이스 |
| I-03 | 공통 응답/예외 계약 세팅 | 훈진 | I-02, P-04 | `ApiResponse`/`ErrorResponse` DTO, `@ControllerAdvice`, 표준 `errorCode` 매핑 |
| I-04 | 품질/문서 게이트 세팅 (Spotless + Swagger) | 훈진 | I-03 | Spotless 규칙 + `spotlessCheck` 통과, Swagger UI(`/swagger-ui/index.html`) 확인 |

### Init 실행 결과 (완료)
- 완료 기준 브랜치: `origin/develop`
- 완료 항목: `I-01`, `I-02`, `I-03`, `I-04`
- 반영 PR:
  - `#12` Init: I-01 Build/Config Baseline 세팅
  - `#13` Init: I-02 DB Baseline + JPA Entity/Repository 세팅
  - `#14` Init: I-03 공통 응답/예외 계약 세팅
  - `#15` Init: I-04 품질/문서 게이트 세팅
- 게이트 판정: `API 구현(F1~F4) 착수 가능`

## API 구현 Task (Next)
| Task ID | 작업 | 우선순위 | Depends On | 주요 산출물 |
| --- | --- | --- | --- | --- |
| F1 | 공지 크롤링/저장 기능 구현 | High | P-03, I-02, I-03 | Jsoup 의존성, 크롤러 서비스, URL 정규화, 중복 저장/업데이트, 크롤링 실행 API |
| F2 | 질문 처리 API 구현 | High | F1, I-03 | 질문 DTO/Validation, 공지 검색 전략, Gemini 연동 경계, AI 실패 예외 처리 |
| F3 | 북마크 CRUD 구현 | Medium | F1, I-02, I-03 | Bookmark Controller/Service/DTO, 저장/조회/삭제 API, 중복/대상 없음 예외 |
| F4 | 대화 이력 CRUD 구현 | Medium | F2, I-02, I-03 | ChatHistory Controller/Service/DTO, 저장/최근 조회/삭제 API |

### 추천 구현 순서
1. `F1` 공지 크롤링/저장
2. `F2` 질문 처리
3. `F3` 북마크
4. `F4` 대화 이력
5. `C-07~C-10` 보안/테스트/배포/핸드오프 정리
