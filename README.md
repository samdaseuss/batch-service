# 청크 기반 배치 서비스
본 브랜치에는 청크 기반 배치 서비스를 구현되어 있습니다.

## 청크 기반 배치 서비스 폴더 구조
청크 기반 배치 서비스의 폴더 구조는 다음과 같습니다.
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

## 테스트 환경
* 독립된 테스트 환경 구성 ( 서로 영향 받지 않게 )
* 테스트는 메모리 디비를 사용하기 때문에 전체 테스트가 돌 때 까지는 데이터베이스에 데이터가 쌓일 수 있다.
* 사용한 레포지토리들에 대해서 클린업 해주는 코드 구현 
* 데이터가 생성됐거나 수정됐거나 했을 떄 아예 데이터를 삭제해버리는 것을 시도.
* 테스트 할 때 데이터를 정리하는 메서드의 이름을 티어 다운이라고 한다.
* afterEach : 각각의 테스트 메서드가 끝난 다음에 이 작업을 수행해주는 어노테이션을 설정해주고 시작

## 테스트 케이스 설명
* success_givenPlainText : 주어진 플레인 텍스트가 없을 때 성공이 되어야 함

## 개발 환경 및 기술 스택
### 개발 환경
### 기술 스택
* Spring Boot 2.5.4
* Gradle 8.5
* Docker 23.0.6
* Spring-batch 4.3.3

## 주요 코드 설명
### domain/PlainText.java
`@DynamicUpdate`
* 다이나믹 업데이트 어노테이션
* 엔티티에서 컬럼의 일부 값만 변경이 되었을 때는 그 값들에 대해서만 쿼리가 실행되도록 설정함
* 관련 개념 : JPA

### repository/PlainTextRepository.java
`Pages<PlainText> findBy(Pageable pageable);`
* 페이징을 통한 데이터 리드를 위해 추가한 쿼리
* pageable 값을 받아서 페이지의 사이즈 만큼 데이터를 읽어냄

## 주요 어노테이션 정리
`@Configration` : 잡에 관련된 빈들을 정의해줍니다.
`@AllArgsConstructor` : 필요한 빈들을 와이어링해오기 위해서 생성자 주입으로 사용하기 위한 롬복의 어노테이션

## 잡을 빌드
잡 빌더 팩토리, 스텝 빌더팩토리
 