# Server Coding Convention

## Package Naming
- Lowercase, singular form (`notice`, `chat`, `bookmark`, `auth`)
- Follow domain-centric structure

## Class Naming
- Suffix required based on role

| Role | Example |
|---|---|
| Controller | `NoticeController` |
| Service (interface) | `NoticeService` |
| Service (impl) | `NoticeServiceImpl` |
| Repository | `NoticeRepository` |
| Entity | `Notice` |
| DTO (request) | `CreateNoticeRequest`, `SearchChatRequest` |
| DTO (response) | `NoticeResponse`, `ChatAnswerResponse` |
| Exception | `NoticeNotFoundException` |

## Variables
- Avoid implicit broad typing in local variables; prefer explicit intent
- Boolean variables must be prefixed with `is` (e.g. `isActive`, `isDeleted`)
- Constants: UPPER_SNAKE_CASE (e.g. `MAX_RETRY_COUNT`)
- Variable names must be meaningful

## Methods
- Name format: verb + noun
  - `get` — retrieve a single item
  - `getList` — retrieve a list
  - `create` — create a new resource
  - `update` — modify an existing resource
  - `delete` — remove a resource
  - `check` — validate logic
  - `convert` — transform input to another form
  - Utility methods returning boolean: prefix with `has`

## Collections
- Prefer `List`, `Map`, `Set` over arrays
- Use streams when they improve readability
- Copy collections with `new ArrayList<>(original)`

## Types
- Always use DTOs for data transfer between layers (never expose Entity directly)
- Define Service as interface, then provide implementation class
- Keep API request/response DTOs separated by use case
