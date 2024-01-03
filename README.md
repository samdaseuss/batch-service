# 배치 서비스

## 배치 서비스 실행 방법
1. 실행 전 Program arguments에 만들어놓은 Job을 설정해줍니다.
```setup
--spring.batch.job.names=batchJob
```
2. 스프링부트 애플리케이션을 실행합니다. 
3. batchJob 이라는 이름으로 SimpleJob 구현체가 생성되어 Job이 실행됩니다.

## 개발 환경 및 기술 스택
### 개발 환경
### 기술 스택
* Spring Boot 2.5.4
* Gradle 8.5
* Docker 23.0.6