# Summary
백엔드와 프론트 2가지 모듈로 구성된 프로젝트입니다.

백엔드 모듈은 사내 admin 프로젝트로 해석하여 진행했습니다.

## 실행 방법
(빌드,구동,테스트 포함)


## backend 스펙
- spring boot : 3.3.4
- java : openjdk 17
- mybatis : 3.0.3
- swagger : springdoc-openapi 2.5.0
- db : h2
- build 툴 : gradle

## 추가 설명
### 패키지 구조 설명
1 

주요 의도는 각 계층간의 의존성을 최소화하고, 각 계층은 역할이 명확한 것입니다.

ex) 응답 포맷이 변경되면 presentation layer(controller)만 수정이 발생한다.

2 

통계성 조회 기능들과 상품&브랜드 관리 기능들을 모두 admin 기능으로 해석하였습니다.

때문에 도메인을 분리하여 확장 가능한 형태가 아닌 간단한 패키지 구조로 변경하였습니다.

만약 조회 기능이 외부 송출 api로 해석했다면 admin, api, frontend와 같이 별도의 모듈로 구성하였을 것입니다.


