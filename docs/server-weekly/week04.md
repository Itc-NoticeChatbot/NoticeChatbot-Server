# 4주차 - 리포지터리와 연관관계

## 핵심 개념
- JPA Repository의 역할
- 질문-답변 1:N 관계 매핑
- `mappedBy` 유무에 따른 테이블 구조 차이
- `CascadeType.REMOVE` 의미
- 메서드 이름 기반 조회 메서드 정의

## 실습 포인트
- `QuestionRepository extends JpaRepository<Question, Integer>`
- `findBySubject(...)` 사용자 정의 메서드

## 교안에서 확인되는 코드 키워드
- `@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)`
- `question_id` (외래키)
- `findBySubject`
