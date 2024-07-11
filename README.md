## 📑Kind of Test

- **`JUnit`** 단위테스트
- **`Mock`** 단위테스트
- **`testcontainers` 통합테스트**
    - MySQL
    - Redis
    - AWS S3
    - Kafka
- **`archunit`**
    - 패키지명-클래스명 코드컨벤션
    - 계층 간 의존성 테스트
- **`jacoco` 테스트코드 커버리지**</br>
  - gradle 실행
    
    ```bash
    $ gradle jacocoTestReport
    ```
  
  - jacoco 테스트 커버리지 리포트</br>
    .\build\jacoco\jacoco.html\index.html
  
- **`spotless` 코드 포맷팅을 자동화**
    
    ```yaml
    //build.gradle
    spotless {
        java {
            googleJavaFormat()
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
    ```

    - **googleJavaFormat()**: Google의 Java 스타일 가이드에 따라 코드를 포맷팅
    - **removeUnusedImports()**: 사용되지 않는 import 문을 자동 제거
    - **trimTrailingWhitespace()**: 각 줄 끝에 있는 불필요한 공백 제거
    - **endWithNewline()**: 파일 끝에 새 줄 추가

    </br>
    
    - gradle pre-commit 생성</br>
      commit 전 spotless 자동 실행 후 commit

      ```yaml
      $ gradle addGitPrecommitHook
      ```
    
    
</br>

## ⚙️Infra

- Docker
    - MySQL
    - Flyway</br>
    관련 이슈: [Fiyway checksum 이슈](https://velog.io/@yeahdy/Flyway-Migration-checksum-mismatch-에러-해결)
