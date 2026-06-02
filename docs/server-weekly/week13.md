# 13주차 - 로컬 밖 배포

## 핵심 개념
- 서버 vs 클라우드
- AWS Lightsail 기반 배포
- JAR 전송/실행
- 시작/중지 스크립트 운영
- 80포트 운영(리버스 프록시)

## 실습 포인트
- JAR 파일 생성 후 서버 실행
- `start.sh`, `stop.sh` 구성
- 8080 -> 80 포트 서비스 노출

## 교안에서 확인되는 코드 키워드
- `nohup java -jar $JAR > $LOG 2>&1 &`
- `kill -9 $BOARD_PID`
- `proxy_pass http://localhost:8080;`
