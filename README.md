# Summary
backend, admin, frontend 3가지 모듈로 구성된 프로젝트입니다.

- backend 모듈 : 고객이 이용하는 외부 송출 api 모듈(통계 리포트 조회)
- admin 모듈 : 관리자 전용 모듈(브랜드/상품 관리)
- (미구현)frontend 모듈 : view를 담당하는 모듈

### 구현 범위
view를 담당하는 frontend 모듈은 구현하지 못하였습니다.

backend, admin 모듈은 구현이 완료되었으며, 아래 "`요청/응답 스펙(정책 포함)` 목차를 통해 테스트 및 적용한 정책들을 확인하실 수 있습니다.

### 구동 방식
h2 인메모리 db를 사용하였으며, 애플리케이션 구동 시 미리 정의해둔 테이블 및 데이터들이 추가됩니다.

즉, 요구사항대로 1개의 상품은 무조건 존재합니다.


#
(추가적으로 프로젝트 전반적인 구조 및 설계 의도 이해를위해 하단 "추가 설명" 목차도 참고해주세요!)

# 모듈 스펙
- spring boot : 3.3.3
- java : openjdk 17
- mybatis : 3.0.3
- spring-data-jpa : 스타터 패키지 기반
- db : h2
- build 툴 : gradle

# 실행 방법
### 1. 빌드
프로젝트 경로에서 아래 명령어를 수행해주세요. 단, 수행 환경은 java가 설치됨을 가정
- `./gradlew clean build`
- 모듈 별 : `./gradlew :backend:clean :backend:build`
- 모듈 별 : `./gradlew :admin:clean :admin:build`

(간혹 디펜던시가 꼬이는 상황이 발생한다면, 인텔리제이 ide 기준 > invalidate caches > 모두 체크 후 '수행')
또는 `./gradlew clean build --refresh-dependencies`

### 2. 구동
1. IDE 구동
2. jar 패키징
- `./gradlew :backend:bootJar`
  - `java -jar backend/build/libs/backend-0.0.1-SNAPSHOT.jar`
  - http://localhost:8080/swagger-ui/index.html
- `./gradlew :admin:bootJar`
  - `java -jar admin/build/libs/admin-0.0.1-SNAPSHOT.jar`
  - http://localhost:9090

3. (비추천) 구동에 문제없지만 favicon 에러로그가 발생하여 비추천
- `./gradlew :backend:bootRun`
- `./gradlew :admin:bootRun`


### 3. 테스트
(프로젝트 루트 경로의 http 디렉터리에 위치)

아래 `요청/응답 스펙` 목차의 http 파일을 순차적으로 확인 부탁드립니다.

# 요청/응답 스펙(정책 포함)
아래 파일 내부에 기능별로 정의되어 있습니다.
- [feature1.http](http/feature1.http)
- [feature2.http](http/feature2.http)
- [feature3.http](http/feature3.http)

- [feature4-1-brand.http](http/feature4-1-brand.http)(정책 포함! 필수 확인!)
- [feature4-2-product.http](http/feature4-2-product.http)(정책 포함! 필수 확인!)



# 프로젝트 설명
프로젝트와 관련하여 추가 설명드리는 목차입니다.

### 3개의 모듈 분리
summary : 3가지 멀티 모듈로 구성하였으며, 각 모듈별로 역할을 구분하기 위함입니다.

외부 고객이 이용하는 송출 api를 backend 모듈, 서비스 운영에 필요한 관리자 프로젝트를 admin 모듈로 정의하였습니다. (과제 설명서 참고)

frontend는 뷰를 담당하는 모듈입니다.

### 패키지 구조
summary : 각 계층 별 역할이 명확히 구분되도록 구성하였습니다.

backend 모듈은 단순한 패키지 구조를 가집니다.(레이어드 아키텍처)

비교적 sql이 복잡하고(mybatis를 채택한 이유) 나머지 로직들은 간단하기에, 네이밍을 포함한 전체 구조를 간단하게 구현하였습니다.

하지만 admin모듈은 `router`라는 레이어가 추가되었습니다.
 `controller > router > service > repository > data`

router는 action(insert,update,delete)을 분류하는 계층으로 이해해주시면 됩니다.

router의 역할은 명확합니다.

적절한 서비스레이어를 호출해주는 것 하나입니다. 때문에 request 객체를 변환하는 등의 행위는 수행하지 않도록 하였습니다.

### backend 모듈(mybatis) vs admin 모듈(jpa)
summary : 각 모듈의 특성을 고려하여 채택하였습니다.

1. 먼저 backend 모듈은 통계성 리포트 api로, sql이 복잡하고 나머지 서비스 로직들은 간단하기에 mybatis로 문제를 풀어가느것이 더 적절하다고 판단했습니다.

2. admin 모듈은 sql이 간단하지만 서비스로직(요구 사항 또는 정책, 예외 처리 등)에 집중된 프로젝트로 spring-data-jpa를 사용하여 간단하게 추가/변경/삭제를 구현했습니다.

# 기타 작업 계획서
업무 진행 전 요구사항 및 스펙을 정의하는 문서로 봐주시면 됩니다.
ex. 초기 분석, 스펙, 브랜치 전략 등을 포함

[PLAN.md](PLAN.md)

