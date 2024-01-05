# 테스클랫 기반 배치 서비스
테스클랫 기반 배치 서비스를 구현한 브랜치입니다.

## 배치 서비스 실행 방법
배치 서비스 실행 방법
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
