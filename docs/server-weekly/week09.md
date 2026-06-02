# 9주차 - 폼과 유효성 검증

## 핵심 개념
- 폼 객체(`QuestionForm`, `AnswerForm`) 도입
- 검증 어노테이션 사용
- 검증 실패 처리(`BindingResult`)

## 실습 포인트
- `@NotEmpty`, `@Size`로 입력 제약
- `@Valid` + `BindingResult`로 검증 결과 분기

## 교안에서 확인되는 코드 키워드
- `implementation 'org.springframework.boot:spring-boot-starter-validation'`
- `@NotEmpty(message="Subject is required.")`
- `@Size(max=100)`
- `public String create(@Valid QuestionForm questionForm, BindingResult bindingResult)`
