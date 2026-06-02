# NoticeChatbot Server Guide

## Project Summary
- One-line definition: NoticeChatbot Server는 공지 크롤링 데이터와 챗봇 응답을 제공하는 Spring Boot 기반 API 서버입니다.
- Problems to solve:
  - 공지 원천 데이터의 수집/정규화 자동화 필요
  - 자연어 질문에 대해 관련 공지를 빠르게 추출할 API 필요
  - 북마크/대화 로그 등 사용자 상태를 안정적으로 저장할 백엔드 필요
- Core values:
  - 안정적인 데이터 파이프라인
  - 명확한 API 계약과 예측 가능한 응답
  - 확장 가능한 서비스 계층 구조
- Target users: NoticeChatbot Client 및 운영자

## Tech Stack (Server)
- Language: Java 17
- Framework: Spring Boot 3.4.6
- Build Tool: Gradle (Kotlin DSL)
- Data Access: Spring Data JPA
- DB: MySQL
- HTML Parsing: Jsoup (planned)
- AI Integration: Gemini API (planned)

## Coding Convention
- Follow the coding convention in `docs/rules/coding-convention.md`
- @docs/rules/coding-convention.md

## Git Convention
- Follow the Git convention (commit & PR) in `docs/rules/git-convention.md`
- @docs/rules/git-convention.md

## Server Task Docs
- Treat this repository's `docs/` directory as the source of truth for server work.
- Task order: `docs/server-task/server-task-order.md`
- Gate specs: `docs/server-task/gate/`
- Weekly course notes: `docs/server-weekly/`
- Do not rely on workspace-level parent-folder docs for team-facing server work.

## Work Policy
- 작업 시작 전 변경 범위와 영향 레이어(Controller/Service/Repository)를 먼저 공유한다.
- 작업 완료 후 `./gradlew test` 또는 `./gradlew build`로 검증한다.
- API 계약 변경 시 PR 설명에 요청/응답 변경점을 반드시 포함한다.

## Local Branch Review
- Branch review skill: `skills/branch-review/SKILL.md`
- Issue/branch planning skill: `skills/issue-branch-task-planner/SKILL.md`
- Notion task breakdown skill: `skills/notion-server-task-breakdown/SKILL.md`
- 리뷰 기준 브랜치: `origin/develop`
