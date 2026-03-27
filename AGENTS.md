# Spring Boot

이 저장소는 Spring Boot 애플리케이션입니다.

## 프로젝트 구조

- `src/main/java`: 애플리케이션 코드
- `src/main/resources`: 설정 파일, 템플릿, SQL, 정적 리소스
- `src/test/java`: 테스트 코드
- `build.gradle`: 빌드 설정

## 작업 원칙

- 기존 패키지 구조와 코드 스타일을 유지한다.
- 작은 변경으로 해결 가능하면 큰 리팩토링을 하지 않는다.
- 사용자가 요청하지 않은 라이브러리 추가, 설정 변경, DB 스키마 변경은 **절대** 하지 않는다.
- 설정값을 추가하거나 변경할 때는 `application.yml`와 관련 문서를 함께 확인한다.
- API 응답 형식이 바뀌면 관련 테스트와 문서도 같이 수정한다.
- 하드코딩보다 설정 주입(`@Value`, `@ConfigurationProperties`)을 우선한다.
- 비즈니스 로직은 Controller 클래스에 두지 않고 ~Service 클래스에 둔다.
- 예외 처리는 기존 프로젝트 방식(`@ControllerAdvice` 등)에 맞춘다.

## 구현 가이드

- 새 API를 추가할 때:
    - Controller
    - Service
    - 필요한 경우 DTO/Entity/Repository
    - 테스트
      를 함께 작성한다.
- 입력 검증이 필요하면 `javax.validation` / `jakarta.validation` 기반으로 처리한다.
- JPA를 쓴다면 N+1, 불필요한 eager loading, 트랜잭션 범위를 주의한다.
- 로그에는 민감정보를 남기지 않는다.

## 자주 쓰는 명령어

### Gradle
- 빌드: `./gradlew build`
- 테스트: `./gradlew test`
- 특정 테스트: `./gradlew test --tests "com.example.user.UserServiceTest"`

## 테스트 원칙
- AssertJ의 메서드를 활용
- 테스트는 //given, //when, //then 형식으로 작성

## 완료 조건

- 관련 테스트가 통과한다.
- 컴파일 에러가 없다.
- 변경한 기능에 맞는 테스트가 추가 또는 수정되어 있다.
- 요청한 동작이 재현 가능하게 설명된다.

## 응답 방식

- 작업 전 관련 파일과 진입점을 먼저 확인한다.
- 변경 이유가 있으면 짧게 설명한다.
- 완료 시 수정한 파일, 핵심 변경점, 실행한 테스트 결과를 간단히 정리한다.
