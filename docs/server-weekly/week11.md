# 11주차 - 스프링 시큐리티

## 핵심 개념
- 인증(Authentication)과 인가(Authorization)
- 시큐리티 기본 설정 클래스 구성
- 요청 경로 접근 제어

## 실습 포인트
- 시큐리티 의존성 추가 후 기본 차단 동작 확인
- 학습 단계에서 전체 경로 임시 허용 설정

## 교안에서 확인되는 코드 키워드
- `implementation 'org.springframework.boot:spring-boot-starter-security'`
- `@Configuration`
- `@EnableWebSecurity`
- `SecurityFilterChain`
- `.requestMatchers("/**").permitAll()`
