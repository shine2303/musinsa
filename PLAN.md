진행에 앞서 업무 분석 및 계획

# 1. 업무 분석

### 구현 X
- 로깅
- 모니터링
- ci/cd
- 인증 및 권한

### 가산점

- 단위테스트, 통합테스트
- view 구현

### 요구사항
- 8개의 카테고리에서 상품을 하나씩 구매하여 코디를 완성한다.
- 가격 외 부가적인 비용 고려하지않으며, 품절또한 없다. 
- 단, 최초 1개의 상품은 존재한다.

### 구현 기능
public인 repo로 보안상 명시X

### 고려 사항
- 응답 실패 시, 실패 값과 사유를 전달해줄 것
- 응답 포맷을 충분히 고민할 것

# 2. 작업 설계

### 스펙
- backend 모듈 : 고객이 조회하는 api용 모듈(응답포맷이 json으로 restcontroller 사용)
  - spring boot : 3.3.4 (ga 중 최신)
  - java : openJDK 17(boot3은 최소 유구사항이 jdk 17, lts, 무료 jdk)
  - mybatis : 복잡한 통계성 쿼리
  - swagger : boot3부터는 springdoc을 사용해야함
  - h2 사용
- admin 모듈 : 상품,브랜드 관리 전용 모듈
  - spring boot : 3.3.4 (ga 중 최신)
  - java : openJDK 17(boot3은 최소 유구사항이 jdk 17, lts, 무료 jdk)
  - jpa : 간단한 기능으로 data jpa 사용
  - swagger : boot3부터는 springdoc을 사용해야함
  - h2 사용
- frontend 모듈 : backend 모듈 구현 완료 후 진행 

### 브랜치 전략
1. 기본 설정 피처
2. 메인 피처 브랜치로 musinsa_project 사용. 해당 브랜치에서 브랜치따서 작업 진행

### 테이블 설계
- 브랜드 : id, name
- 상품 : id, 브랜드 id, 카테고리 id, 가격
- 카테고리 : id, name

### 기본 응답 포맷
```json
{
    "success": true,
    "code": 200,
    "message": "SUCCESS",
    "data": { },
    "timestamp":""
}
```
### 에러 응답 포맷
```json
{
    "success": false,
    "code": 404 ,
    "message": "리소스를 찾을 수 없습니다",
    "data": null,
    "timestamp": ""
}
```

# 3. 작업 계획

#### 기본 설정
- 모듈 분리
- 패키지 구조
- swagger 설정
- 응답 클래스
- 예외 처리
- h2 설정
- mybatis 설정
- profile 분리 생략

pr : https://github.com/shine2303/musinsa/pull/1

#### admin 기능 개발
- 브랜드 erd
- 상품 erd
- 브랜드 crud
- 상품 crud
- 테스트 코드
  - 유니크 위반
#### be 기능 개발
- 테이블 생성 및 테스트 데이터 인입 기능 -> 자동화
- 기능 1 & 단위테스트 & 통합테스트
- 기능 2 & 단위테스트 & 통합테스트
- 기능 3 & 단위테스트 & 통합테스트
- 기능 4 & 단위테스트 & 통합테스트
#### fe 기능 개발

# 4. 기능 명세
swagger 대체