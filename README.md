# 배치 서비스
스프링부트 프레임워크의 스프링 배치를 이용해 구현한 다양한 배치 서비스를 담은 레포지토리 입니다. 아래의 목차를 이용해 원하는 정보를 빠르게 찾아보세요.
( 본 프로젝트 파일을 실행해보기 위해서는 몇 가지 설정이 필요합니다. 실행을 위해 [튜토리얼](#튜토리얼) 섹션을 먼저 진행해주세요. )

## 배치 서비스 목차
1. [테스클랫 기반 배치 서비스](https://github.com/samdaseuss/batch-service/tree/tasklet)
2. [청크 기반 배치 서비스](https://github.com/samdaseuss/batch-service/tree/chunk)

## 튜토리얼
본 레포지토리의 배치 서비스들을 실행하기 위해 기본적으로 설정해야 하는 정보를 담고 있는 튜토리얼 입니다.
### 배치 서비스 실행 방법
배치 서비스는 2가지 방법 중 1가지를 선택해 실행할 수 있습니다.
#### 1. 인텔리제이 실행
1. 실행 전 Program arguments에 만들어놓은 Job을 설정해줍니다.

(1) batchJob 실행
```setup
--spring.batch.job.names=batchJob
```
(2) advancedJob 실행
```setup
--spring.batch.job.names=advancedJob -targetDate=2024-01-14
```

2. 스프링부트 애플리케이션을 실행합니다. 
3. batchJob 이라는 이름으로 SimpleJob 구현체가 생성되어 Job이 실행됩니다.

#### 2. 빌드 후 실행
1. 프로젝트 터미널에 아래 명령어를 입력합니다.
```terminal
./gradlew bootJar
```
2. java 명령어를 통해 build 진행합니다.
```terminal
java -jar ${빌드한파일}.jar --spring.batch.job.names=${잡이름}
```
* build > libs > api.introduction-to-spring-batch-0.0.1-SNAPSHOT.jar 파일을 가지고 build 진행
* java -jar build/libs/api.introduction-to-spring-batch-0.0.1-SNAPSHOT.jar --spring.barch.job.names=barchJob
* 윈도우/맥에서 빌드한 파일이 있는 곳 까지 명령프롬프트/터미널을 통해 접근해서 실행

### 테스트 환경 구성 방법
테스트 환경에서 Job을 실행하기 위해 다음 설정을 수행해야 합니다.
* 테스트 런타임 설정 - H2 설정
* 배치 테스트를 위한 설정
  * @SpringBatchTest 설정
    * JobLauncherTestUtils : 잡을 실행할 수 있는 테스트 유틸
    * 이 유틸을 사용하기 위해서 스프링 배치 테스트 어노테이션을 선언 해줘야함
#### H2 설정
* 테스트 할 때 런타임으로 h2 데이터 베이스를 사용하기 위해 아래 의존성을 build.gradle에 추가해줍니다.
```groovy
testRuntimeOnly 'com.h2database:h2'
```
* application.yml 파일에 다음을 설정해주세요.
```
spring:
    config:
        activate:
        on-profile: test
    jpa:
        database: h2
```
#### Batch Test를 위한 설정

## 개발 환경 및 기술 스택
### 개발 환경
### 기술 스택
* Spring Boot 2.5.4
* Gradle 8.5
* Docker 23.0.6

## 주요 코드 설명