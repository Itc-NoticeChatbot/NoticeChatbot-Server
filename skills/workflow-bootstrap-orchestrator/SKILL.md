---
name: workflow-bootstrap-orchestrator
description: Orchestrate the Itc_NoticeChatbot end-to-end flow from Notion task to GitHub issue, branch plan, implementation handoff, review gate, and PR publication by reusing existing review/PR skills. Use when the user asks to run the full workflow for this chatbot project.
---

# Workflow Bootstrap Orchestrator

## Pipeline

1. `notion-task-to-github-issue`
2. `issue-branch-task-planner`
3. Implementation handoff (coding phase)
4. Existing review skill (`branch-review`)
5. Existing PR/push skill (team-standard skill already in use)

## Orchestration Rules

- Execute steps strictly in order.
- Stop immediately on failed step.
- Report:
  - step result
  - failure cause
  - next retry action
- Do not skip the review gate.
- Do not replace existing validated review/PR skills with new ones.

## Minimal Inputs

- Notion task URL/ID
- target repo (`owner/repo`)
- scope (`client`, `server`, `both`)

## Final Output

```text
Workflow summary
1) Issue: ...
2) Branch: ...
3) Review: pass/fail
4) PR: ...
```

## 한국어 번역

### 워크플로우 부트스트랩 오케스트레이터
Notion Task부터 GitHub 이슈, 브랜치 계획, 구현 핸드오프, 리뷰 게이트, PR 발행까지 전체 흐름을 순서대로 오케스트레이션한다.

### 파이프라인
1. `notion-task-to-github-issue`
2. `issue-branch-task-planner`
3. 구현 핸드오프(코딩 단계)
4. 기존 리뷰 스킬(`branch-review`)
5. 기존 PR/push 스킬(팀 표준)

### 규칙
- 단계를 반드시 순서대로 실행.
- 실패 시 즉시 중단.
- 각 단계마다 다음을 보고.
  - 단계 결과
  - 실패 원인
  - 재시도 액션
- 리뷰 게이트 생략 금지.
- 검증된 기존 리뷰/PR 스킬을 신규 스킬로 대체 금지.

### 최소 입력
- Notion task URL/ID
- target repo (`owner/repo`)
- scope (`client`, `server`, `both`)

### 최종 출력
```text
Workflow summary
1) Issue: ...
2) Branch: ...
3) Review: pass/fail
4) PR: ...
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
