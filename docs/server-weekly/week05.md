# 5주차 - 리포지터리 테스트 심화

## 핵심 개념
- `@SpringBootTest` 기반 통합 테스트
- CRUD 테스트(저장/조회/삭제)
- `Optional` 처리
- 파생 쿼리 메서드 확장
- 지연 로딩 관련 `@Transactional` 필요성

## 실습 포인트
- `questionRepository.save(...)`
- `findAll`, `findById`, `findBySubjectLike`
- 테스트 검증(`assertEquals`)

## 교안에서 확인되는 코드 키워드
- `@Transactional`
- `findBySubjectAndContent`
- `findBySubjectLike`
