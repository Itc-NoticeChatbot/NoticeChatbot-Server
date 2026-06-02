# P-04 공통 응답/에러 포맷 확정

## 성공 응답
```json
{
  "success": true,
  "data": {},
  "message": "ok"
}
```

## 실패 응답
```json
{
  "success": false,
  "errorCode": "NOTICE_NOT_FOUND",
  "message": "공지 데이터를 찾을 수 없습니다.",
  "details": null
}
```

## HTTP Status 매핑
- `200 OK`: 조회/수정 성공
- `201 Created`: 생성 성공
- `400 Bad Request`: 입력 검증 실패
- `404 Not Found`: 대상 없음
- `409 Conflict`: 중복/상태 충돌
- `500 Internal Server Error`: 서버 처리 실패
- `503 Service Unavailable`: 외부 의존성(크롤링/AI) 일시 실패

## 에러코드 사전(v1)
- `INVALID_REQUEST`
- `VALIDATION_FAILED`
- `NOTICE_NOT_FOUND`
- `NOTICE_DUPLICATED`
- `CRAWL_SOURCE_UNREACHABLE`
- `CRAWL_PARSE_FAILED`
- `AI_RESPONSE_FAILED`
- `INTERNAL_ERROR`

## 사용 규칙
- 에러 응답은 반드시 `errorCode`를 포함한다.
- 사용자 노출 메시지(`message`)와 내부 원인(`details`)을 분리한다.
