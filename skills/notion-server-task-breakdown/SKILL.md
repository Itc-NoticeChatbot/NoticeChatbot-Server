---
name: notion-server-task-breakdown
description: Use Notion project docs to break server work into very small ordered tasks and architecture setup steps before implementation. Use when the user asks to plan server work order, split tasks, assign owners, or freeze architecture first.
---

# Notion Server Task Breakdown

## 목적
Notion 문서를 근거로 서버 작업을 구현 전에 초소형 단위 task로 분해하고, 아키텍처 세팅 순서를 먼저 확정한다.

## 필수 입력
1. 대상 Notion 페이지/DB (최소: 기획안, 기능명세, 기술스택, architecture)
2. 현재 작업 브랜치
3. 이번 사이클 범위(MVP/추가 범위)

## 절차
1. Notion 근거 수집
- `Task1`에서 문제정의/시나리오/MVP 범위를 읽는다.
- `Task2`에서 F1~F4 기능 입력/처리/출력/예외를 읽는다.
- `Task4`에서 확정 스택과 보류 스택을 분리한다.
- `architecture`에서 레이어/도메인/API/운영정책을 읽는다.

2. 아키텍처 세팅 순서 작성
- Layer 구조 확정
- Domain 필드 확정
- API 계약 확정
- AI 경계(검색 -> 생성) 확정
- 에러/로그/운영 규칙 확정

3. 태스크 초소형 분해
- 한 task는 최대 1개 책임만 갖게 분해한다.
- 최소 단위 예시: DTO 확정, 예외코드 확정, 중복 정책 확정, 테스트 케이스 1묶음 작성.
- 각 task에 `선행 작업`, `완료 기준`, `상태`, `Quick Action`을 반드시 붙인다.

4. 현재 브랜치 즉시 실행 큐 생성
- 현재 브랜치가 `develop`이면 문서/명세/체크리스트 작업만 큐에 넣는다.
- 구현/코드 변경 task는 `브랜치 전환 필요`로 표시한다.
- 매 요청마다 `지금 바로 할 일 3~5개`를 선행순으로 출력한다.

5. 문서 반영
- 결과를 `docs/server-task/server-task-order.md`에 표로 기록한다.
- 상태값은 `대기/진행중/완료/보류` 또는 `Not Started/In Progress/Done/Blocked` 중 하나로 통일한다.

## 브랜치 게이트 규칙 (강조)
- 만약에 현재 작업 브런치가 develop이면 코드 로직 작성은 금지한다.
- `develop` 브랜치에서는 문서화, 태스크 분해, 명세 확정만 수행한다.
- 구현은 별도 작업 브랜치 생성 후 시작한다.

## 출력 형식
```text
작업 순서
1. ...
2. ...

아키텍처 세팅 순서
1. ...
2. ...

초소형 태스크 표
- ID / 작업 / 선행 / 완료기준 / 담당 / 상태 / Quick Action

현재 브랜치 즉시 실행 큐
1. [ID] 작업명 - Quick Action
2. [ID] 작업명 - Quick Action
3. [ID] 작업명 - Quick Action
```

## 한국어 번역

### 노션 기반 서버 태스크 분해
Notion 프로젝트 문서를 근거로 서버 작업을 초소형 단위로 쪼개고, 구현 전 아키텍처 세팅 순서를 확정한다.

### 핵심 흐름
1. `Task1`에서 문제정의/시나리오/MVP 범위를 확인한다.
2. `Task2`에서 F1~F4의 입력/처리/출력/예외를 확인한다.
3. `Task4`에서 확정/보류 스택을 분리한다.
4. `architecture`에서 레이어/도메인/API/운영정책을 확인한다.
5. 결과를 `docs/server-task/server-task-order.md`에 반영한다.

### 브랜치 게이트 규칙
- 만약에 현재 작업 브런치가 develop이면 코드 로직 작성은 금지한다.
- `develop`에서는 문서화/태스크 분해/명세 확정만 수행한다.
- 구현은 별도 작업 브랜치에서 시작한다.

## User Approval Gate

- Before any execution that creates, edits, deletes, publishes, deploys, or triggers external side effects, present a short preview to the user first.
- The preview must include: target, planned action, and expected result.
- Do not execute the action until the user gives explicit approval.
- If the user revises the request, regenerate the preview and request approval again.
- Read-only exploration (inspection, fetch, analysis) may proceed without approval, but any write/action step still requires approval.

## Commit Message Rule

- Commit type must be in English: `feat`, `fix`, `chore`, `docs`, `refactor`, `test`, `style`.
- Commit description (after `type:`) must be written in Korean.
- Format: `type: 한글 메시지`
- Example: `chore: 보일러플레이트 리소스 제거`
