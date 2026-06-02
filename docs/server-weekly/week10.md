# 10주차 - 화면 꾸미기

## 핵심 개념
- 목록 화면 가독성 개선(답변 개수 조건부 표시)
- 공통 네비게이션 바 구성
- Bootstrap JS 연결로 반응형 메뉴 동작
- 템플릿 분리(`layout`, `navbar`) 방향

## 실습 포인트
- `question_list.html` 조건 렌더링
- `layout.html`에 navbar와 script 구성

## 교안에서 확인되는 코드 키워드
- `th:if="${#lists.size(question.answers) > 0}"`
- `<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">`
- `<script th:src="@{/bootstrap.min.js}"></script>`
