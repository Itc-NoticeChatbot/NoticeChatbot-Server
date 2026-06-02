# 6주차 - 경로와 서비스 계층

## 핵심 개념
- 루트 URL과 경로(`/question/list`) 설계
- 리다이렉트 처리
- 기능별 패키지 분리
- 컨트롤러-서비스-리포지터리 계층 분리

## 실습 포인트
- 루트 접속 시 목록으로 리다이렉트
- `QuestionController`에서 Repository 직접 호출 제거
- `QuestionService` 도입

## 교안에서 확인되는 코드 키워드
- `return "redirect:/question/list";`
- `@Service`
- `private final QuestionService questionService;`
