---
name: notion-task-to-github-issue
description: Create GitHub issues from Notion task pages for the Itc_NoticeChatbot workflow using fixed templates and labels. Use when the user asks to sync Notion tasks into GitHub issues for this chatbot project's client/server tracking.
---

# Notion Task -> GitHub Issue

1. Read the target Notion task page.
2. Extract minimum fields:
- task title
- task description
- scope (`client`, `server`, or `both`)
- done criteria
3. Build issue metadata:
- title convention: `Init: ...` (bootstrap/setup), `Feat: ...` (feature implementation), `Fix: ...`, `Docs: ...`
- labels: scope label + work type label (`feature`, `fix`, `docs`)
4. Build issue body:
- Problem
- Goal
- Scope
- Checklist
- Done criteria
5. Create GitHub issue with `gh issue create`.
6. Return:
- issue URL
- issue number
- labels used

## Rules

- Do not create an issue without user-confirmed repository target.
- If scope is `both`, create two issues (`client` and `server`).
- Keep checklist action-oriented and testable.
- Do not use `[Client]` / `[Server]` in issue titles; repository path already indicates scope.

## Output Format

```text
Issue created
- Repo: owner/repo
- Number: #123
- URL: https://github.com/owner/repo/issues/123
- Labels: client, feature
```

## 한국어 번역

### 노션 Task -> GitHub 이슈
1. 대상 노션 Task 페이지를 읽는다.
2. 최소 필드를 추출한다.
- Task 제목
- Task 설명
- 범위(`client`, `server`, `both`)
- 완료 기준
3. 이슈 메타데이터를 구성한다.
- 제목 컨벤션: `Init: ...`(초기세팅), `Feat: ...`(기능개발), `Fix: ...`, `Docs: ...`
- 라벨: 범위 라벨 + 작업 유형 라벨(`feature`, `fix`, `docs`)
4. 이슈 본문을 구성한다.
- Problem
- Goal
- Scope
- Checklist
- Done criteria
5. `gh issue create`로 이슈를 생성한다.
6. 결과를 반환한다.
- issue URL
- issue number
- 사용한 labels

### 규칙
- 저장소 대상이 사용자에게 확인되기 전 이슈 생성 금지.
- 범위가 `both`면 `client`, `server` 이슈를 각각 생성.
- 체크리스트는 실행 가능하고 테스트 가능한 항목으로 작성.
- 이슈 제목에 `[Client]`, `[Server]` 접두사는 사용하지 않는다. 저장소 경로로 범위가 이미 구분된다.

### 출력 형식
```text
Issue created
- Repo: owner/repo
- Number: #123
- URL: https://github.com/owner/repo/issues/123
- Labels: client, feature
```

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
