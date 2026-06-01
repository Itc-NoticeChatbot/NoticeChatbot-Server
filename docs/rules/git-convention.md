# Git Convention (Commit & PR Convention)

## Git Flow
- `main`: production-ready, always stable
- `develop`: integration branch; feature branches branch off and merge back here
- `feature`: branch from `develop` -> open PR back to `develop` when done
- Release: merge `develop` into `main` when stable

## Branch Naming
- Format: `feat/description/#issue-number`
- Examples:
  - `feat/notice-crawler/#43`
  - `fix/chat-response-validation/#12`

## Commit Message
- Format: `type: description`
- Examples:
  - `feat: 공지 수집 스케줄러 추가`
  - `fix: 챗봇 응답 매핑 예외 처리`
  - `chore: 의존성 업데이트`
- Keep commits small and atomic

## PR Title
- Format: `Type: description` or `[Type] description`
- Examples:
  - `Feat: 공지 조회 API 추가`
  - `[Fix] 챗봇 질의 파싱 버그 수정`

## PR Rules
- Keep PRs small; write documentation thoroughly
- Describe: problem -> approach considered -> result
- Submit early and iterate with team feedback
- Share unknown risks/issues quickly

## Labels
| Label | When to use |
|---|---|
| `chore` | dependency/tooling changes |
| `docs` | documentation only |
| `feature` | new feature development |
| `fix` | bug fixes |
| `refactor` | refactoring (no behavior change) |
| `style` | non-functional style changes |
| `test` | test code |
