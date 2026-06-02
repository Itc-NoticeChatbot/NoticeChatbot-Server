# NoticeChatbot Server Docs

This directory is the source of truth for server-side planning, rules, and task handoff.

## Main Docs
- `server-task/server-task-order.md`: Current server task order, gate status, and next implementation queue
- `server-task/gate/`: Pre-implementation gate decisions for DB, schema, crawling, and API response format
  - Includes bookmark schema handoff memo for `dev`/`prod` DB validation environments
- `server-weekly/`: Weekly class concept notes used as project references
- `rules/`: Team coding and Git conventions

## Current Work Order
1. `F1` notice crawling/storage
2. `F2` chat question API
3. `F3` bookmark CRUD
4. `F4` chat history CRUD
5. `C-07~C-10` security, tests, deployment docs, final handoff

## Working Rule
- Do not implement feature logic directly on `develop`.
- Create a feature branch from the latest `origin/develop`.
- Keep task docs in this repository updated before opening or handing off work.
