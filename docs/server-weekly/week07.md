# 7주차 - 상세/저장 기능 + 디자인 기초

## 핵심 개념
- 상세 페이지 라우팅(`@PathVariable`)
- 답변 저장 폼 전송(`th:action`, `textarea`, `input`)
- 처리 후 상세 페이지 리다이렉트
- CSS/Bootstrap 기반 화면 스타일 적용

## 실습 포인트
- 목록 -> 상세 -> 답변 등록 흐름 연결
- `style.css`, `bootstrap.min.css` 적용

## 교안에서 확인되는 코드 키워드
- `@GetMapping("/question/detail/{id}")`
- `<form th:action="@{|/answer/create/${question.id}|}" method="post">`
- `String.format("redirect:/question/detail/%s", id)`
- `<link rel="stylesheet" type="text/css" th:href="@{/style.css}">`
