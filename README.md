# Summary
backend, admin, frontend 3가지 모듈로 구성된 프로젝트입니다.

- backend 모듈 : 고객이 이용하는 외부 송출 api 모듈
- admin 모듈 : 관리자 전용 모듈
- frontend 모듈 : view를 담당하는 모듈

프로젝트의 빠른 이해를위해 하단 "추가 설명" 목차를 참고해주세요.

# 실행 방법
### 빌드
프로젝트 경로에서 아래 명령어를 수행해주세요. 단, 수행 환경은 java가 설치됨을 가정
- `./gradlew clean build`
- 모듈 별 : `./gradlew :backend:clean :backend:build`
- 모듈 별 : `./gradlew :admin:clean :admin:build`

(간혹 디펜던시가 꼬이는 상황이 발생하는데, 인텔리제이기준 > invalidate caches > 모두 체크 후 '수행')

### 구동
1. IDE 구동
2. jar 패키징
- `./gradlew :backend:bootJar`
  - `java -jar backend/build/libs/backend-0.0.1-SNAPSHOT.jar`
- `./gradlew :admin:bootJar`
  - `java -jar admin/build/libs/admin-0.0.1-SNAPSHOT.jar`

3. (비추천) 구동에 문제없지만 favicon 에러로그가 발생하여 비추천
- `./gradlew :backend:bootRun`
- `./gradlew :admin:bootRun`


### 테스트
프로젝트 루트 경로의 http 디렉토리를 참고해주세요.

- [feature1.http](http/feature1.http)
- [feature2.http](http/feature2.http)
- [feature3.http](http/feature3.http)

- [feature4-1-brand.http](http/feature4-1-brand.http)
- [feature4-2-product.http](http/feature4-2-product.http)

# 모듈 스펙
- spring boot : 3.3.3
- java : openjdk 17
- mybatis : 3.0.3
- spring-data-jpa : 스타터 패키지 기반
- db : h2
- build 툴 : gradle

# 요청/응답 스펙
아래 파일 내부에 기능별로 정의되어 있습니다.
- [feature1.http](http/feature1.http)
- [feature2.http](http/feature2.http)
- [feature3.http](http/feature3.http)

- [feature4-1-brand.http](http/feature4-1-brand.http)
- [feature4-2-product.http](http/feature4-2-product.http)


# 추가 설명
### 모듈 분리 이유
3가지 멀티 모듈로 구성하였으며, 각 모듈별로 역할을 구분.

backend는 고객이 이용하며 admin은 고객이 아닌 관리지를 위한 프로젝트(요구사항 기제)

frontend는 뷰를 담당

### 패키지 구조 설명
각 계층 별 역할이 명확히 구분되도록 구성하였습니다.

backend 모듈은 단순한 패키지 구조를 가집니다.
하지만 admin모듈은 레이어가 추가되었습니다.

`controller > router > service > repository > data`

action(insert,update,delete)을 분류하는 계층으로 이해해주시면 됩니다.

# 기타 작업 계획서
아래 작업 계획서에는 실무에서의 진행과 유사하게 작성되었습니다. 

ex. 초기 분석, 스펙, 브랜치 전략 등을 포함

[PLAN.md](PLAN.md)

