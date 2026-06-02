---
name: issue-branch-task-planner
description: Generate branch names and execution checklists from GitHub issues for the Itc_NoticeChatbot project, then share a concrete work plan before coding. Use when implementation starts from a client/server issue in this project.
---

# Issue -> Branch + Task Plan

## Mandatory Workflow Order
1. Check task/issue scope and constraints.
2. Create issue (if missing).
3. Create/switch feature branch.
4. Implement logic.
5. Run review (mandatory) using `branch-review` skill with user-provided focus points.
6. Reflect review findings and commit by atomic unit.
7. Open PR.
8. Merge PR.

## Planning Steps
1. Read issue title/body and identify:
- scope (`client` or `server`)
- deliverables
- constraints
2. Generate branch name:
- format: `feat/description/#issue-number` (or `fix/...`)
3. Propose execution checklist:
- files/modules to touch
- implementation steps
- validation steps
4. Share plan to user before coding.
5. After user approval, create/switch branch.

## Rules

- Never start coding before plan is shared.
- Keep checklist short and measurable.
- Include explicit test/build commands for the target scope.
- Review step is mandatory before PR/merge.
- Commit messages must be written in Korean (no English commit subject).
- Commit must be made whenever an atomic unit of work is completed.

## Output Format

```text
Work plan
1. ...
2. ...
3. ...

Workflow
1. Task check
2. Issue setup
3. Branch creation
4. Implementation
5. Review (mandatory)
6. PR
7. Merge

Branch
- feat/notice-search-api/#41
```

## 한국어 번역

### 이슈 -> 브랜치 + 작업 계획

### 필수 워크플로우 순서
1. Task/이슈 범위와 제약 확인
2. 이슈 생성(없으면 생성)
3. 기능 브랜치 생성/전환
4. 로직 구현
5. 리뷰 수행(필수): 사용자 리뷰 포인트 기반 `branch-review` 스킬 사용
6. 리뷰 반영 후 원자 단위 커밋
7. PR 생성
8. PR 머지

### 계획 절차
1. 이슈 제목/본문을 읽고 다음을 식별한다.
- 범위(`client` 또는 `server`)
- 산출물
- 제약사항
2. 브랜치명을 생성한다.
- 형식: `feat/description/#issue-number` (또는 `fix/...`)
3. 실행 체크리스트를 제안한다.
- 수정 대상 파일/모듈
- 구현 단계
- 검증 단계
4. 코딩 전에 사용자에게 계획을 공유한다.
5. 사용자 승인 후 브랜치를 생성/전환한다.

### 규칙
- 계획 공유 전 코딩 금지.
- 체크리스트는 짧고 측정 가능하게 작성.
- 대상 범위에 맞는 테스트/빌드 명령을 명시.
- PR/머지 전에 리뷰 단계는 반드시 수행한다.
- 커밋 메시지는 반드시 한글로 작성한다 (영문 제목 금지).
- 원자 단위 작업이 완료될 때마다 커밋한다.

### 출력 형식
```text
Work plan
1. ...
2. ...
3. ...

Workflow
1. Task check
2. Issue setup
3. Branch creation
4. Implementation
5. Review (mandatory)
6. PR
7. Merge

Branch
- feat/notice-search-api/#41
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
