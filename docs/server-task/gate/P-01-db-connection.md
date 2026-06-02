# P-01 DB 연결 정보 확정

## 확정안
- DBMS: MySQL 8.0
- 문자셋: `utf8mb4`
- 콜레이션: `utf8mb4_unicode_ci`
- 타임존: `Asia/Seoul`

## 환경변수 키
- `DB_HOST`
- `DB_PORT` (기본값 `3306`)
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `JPA_DDL_AUTO` (권장: `validate` 또는 `update`)

## Spring 설정 템플릿
```yaml
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT:3306}/${DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&connectionTimeZone=Asia/Seoul
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: ${JPA_DDL_AUTO:update}
    properties:
      hibernate:
        format_sql: true
```

## 운영 규칙
- 로컬/배포 환경 모두 환경변수 기반으로만 관리한다.
- DB 비밀번호는 저장소에 커밋하지 않는다.
