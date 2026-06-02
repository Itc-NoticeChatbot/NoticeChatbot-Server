---
name: branch-review
description: Diff-based local code review workflow for the current server branch before push. Use when the user asks for "code review", "review this branch", "pre-push check", or asks to review with focus points such as API contract consistency, transactional boundaries, validation, and exception handling.
---

# Branch Review Skill (Server)

Run a selective review on the current branch only, based on explicit review focus points.

## Steps

1. Ask for review focus points first (mandatory).
   - Do not start review without user-provided focus points.
   - If missing, ask again and pause.
   - Include the exact user-provided focus points in the final report.

2. Collect branch scope artifacts.

```bash
bash ./tools/branch-review/collect_scope.sh [base-ref]
```

Default base ref: `origin/develop`

3. Review changed files/lines first (`files.txt`, `diff.patch`), then expand to nearby context only if needed.

4. Return results in this format:
   - First line must include: `[SKILL ACTIVE] branch-review`
   - A section named: `Review Focus Points (User Input)`
   - Priority list: `High`, `Medium`, `Low`
   - Each finding: `file:line`, why it matters, short fix suggestion
   - Final section: `Refactoring Priority Queue`

## Rules

- Do not review unrelated untouched areas unless required for impact analysis.
- Prefer actionable findings over style-only comments.
- If no issues are found, explicitly state that.

## 한국어 번역

### 브랜치 리뷰 스킬 (서버)
현재 서버 브랜치에 대해서만, 사용자가 지정한 리뷰 포인트를 기준으로 선택적 코드 리뷰를 수행한다.

### 절차
1. 반드시 먼저 사용자에게 리뷰 포인트를 받는다.
- 사용자 입력 없이 리뷰를 시작하지 않는다.
- 포인트가 없으면 다시 요청하고 중단한다.
- 최종 보고서에 사용자 입력 포인트를 그대로 포함한다.

2. 브랜치 범위 산출물을 수집한다.

```bash
bash ./tools/branch-review/collect_scope.sh [base-ref]
```

기본 `base-ref`: `origin/develop`

3. 변경 파일/라인(`files.txt`, `diff.patch`)을 먼저 리뷰하고, 필요할 때만 인접 문맥을 확장해서 확인한다.

4. 결과는 다음 형식으로 반환한다.
- 첫 줄: `[SKILL ACTIVE] branch-review`
- `Review Focus Points (User Input)` 섹션 포함
- 우선순위: `High`, `Medium`, `Low`
- 각 이슈: `file:line`, 영향 설명, 짧은 수정 제안
- 마지막: `Refactoring Priority Queue`

### 규칙
- 영향 분석이 필요하지 않으면 변경되지 않은 영역은 리뷰하지 않는다.
- 스타일 지적보다 실행 가능한 개선사항을 우선한다.
- 문제를 찾지 못했으면 그 사실을 명시한다.

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
