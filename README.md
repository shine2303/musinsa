# Summary
backend, admin, frontend 3가지 모듈로 구성된 프로젝트입니다.

- backend : 고객이 이용하는 외부 송출 api 모듈
- admin : 관리자 전용 모듈
- frontend : view를 담당하는 모듈

프로젝트 이해에 필요한 상세 내용은 하단 "추가 설명" 목차를 참고해주세요.

## 실행 방법
(빌드,구동,테스트 포함)


## backend 스펙
- spring boot : 3.3.4
- java : openjdk 17
- mybatis : 3.0.3
- swagger : springdoc-openapi 2.5.0
- db : h2
- build 툴 : gradle

### 모듈 분리 이유
3가지 멀티 모듈로 구성하였다. 각 모듈별로 역할을 구분하기 위함이다.

backend는 고객이 이용하는 프로젝트가 된다. (요구사항)

admin은 고객이 아닌 관리지를 위한 프로젝트다. 관리자란 서비스 운영에 관여하는 개발자를 포함한 운영자로 정의한다.


### 패키지 구조 설명
주요 의도는 각 계층간의 의존성을 최소화하고, 각 계층 별 역할을 명확히 하는 것입니다.

ex) 응답 포맷이 변경되면 presentation layer(controller)만 수정이 발생한다.



