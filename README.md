# 청크 기반 배치 서비스
본 브랜치에는 청크 기반 배치 서비스를 구현되어 있습니다.

## 청크 기반 배치 서비스 폴더 구조
청크 기반 배치 서비스의 폴도 구조는 다음과 같습니다.
```
com.samdaseuss.api.introductiontosprignbatch
 ﾤ 💾core
    ﾤ 💾domain
         ﾤ 📄PlainText.java
    ﾤ 💾repository
         ﾤ 📄PlainTextRepository.java
 ﾤ 💾job
    ﾤ 📄JobConfig
 📄Application
```
* domain : 엔티티를 선언하는 도메인 패키지
* repository : 레포지토리를 선언하는 패키지

## 청크 기반 배치 서비스 실행 방법
청크 기반 배치 서비스는 크게 2가지 방법으로 실행이 가능합니다.
### 1. 인텔리제이 실행
1. 실행 전 Program arguments에 만들어놓은 Job을 설정해줍니다.
```setup
--spring.batch.job.names=batchJob
```
2. 스프링부트 애플리케이션을 실행합니다. 
3. batchJob 이라는 이름으로 SimpleJob 구현체가 생성되어 Job이 실행됩니다.

### 2. 빌드 후 실행
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

## 개발 환경 및 기술 스택
### 개발 환경
### 기술 스택
* Spring Boot 2.5.4
* Gradle 8.5
* Docker 23.0.6

## 주요 코드 설명
### domain/PlainText.java
`@DynamicUpdate`
* 다이나믹 업데이트 노어테이션
* 엔티티에서 컬럼의 일부 값만 변경이 되었을 때는 그 값들에 대해서만 쿼리가 실행되도록 설정함
* 관련 개념 : JPA

