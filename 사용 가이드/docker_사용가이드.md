## Container 가상화
<img align="left" width="250" height="250" src="https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/ee8aa6d0-e87f-4cba-bc5a-a09452e2108e">

- 하이퍼바이저(Hypervisor) 대신 가상컨테이너가 존재
- 중복 코드가 있을 경우 사용하지 않을 수 있음
- 게스트OS 자체가 설치되는 것이 아닌 소프트웨어(Docker엔진)만 있으면 가상화 실행을 위한 필요한 것만 실행하면 됨
- 호스트PC가 가지고 있는 리소스와 도커 엔진이 가지고 있는 리소스를 같이 공유, 사용 가능</br>
 → 최소한의 내용으로 실행가능 해서 속도 빠름

</br></br></br></br>

## Container Image
**Container 실행에 필요한 설정 값** → 상태값X, 불변

<img align="left" width="250" height="250" src="https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/7b74cd29-2f29-454f-9e7c-1c9e7ac15a34">

- Image를 가지고 실체화 → Container
- MySQL Image
→ MySQL 서버를 실행할 때 필요한 파일, 포트정보, 실행명령어 등을 포함
- 컨테이너를 실행하기 위한 모든 정보를 갖고 있어서 의존성 컴파일, 설치 필요 X</br>
→ Image로 소프트웨어, 운영체제 실행 가능
- **Registry** 저장소에 필요한 이미지를 올림

</br></br></br></br></br>

## Docker Host

생성된 이미지를 사용할 수 있는 컨테이너 서버 </br>
<p align="left">
<img width="500" height="300" src="https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/6ef5678d-320d-40d2-b011-3f9a63810eec">
</p>

- **Local Repository**
도커 호스트에서 실행할 수 있는 컨테이너가 저장되어 있는 자체 Local Repository
public Registry, private Registry 에서 이미지 다운로드 받아서 local Repository에 저장
- **Container 생성**
Local Repository에 저장된 이미지를 통해 컨테이너 생성
- **create** 커멘드</br>
 컨테이너 생성
- **start** 커멘드</br>
 생성된 컨테이너를 실행
- **run** 커멘드</br>
 create + start → 로컬 레파지토리에 저장되어 있지 않은 이미지라면 Registry 에서 pull(다운로드)받고 생성과 실행을 해줌

</br>

<img align="left" width="250" height="250" src="https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/e93ded10-19f4-4bde-81f1-3ddb59278a25">

클라이언트에서 사용하기 위한 포트가 공개되어 있을 경우 클라이언트에서 독립적으로 운영될 수 있는 서비스처럼 실행해서 사용 가능 

- MySQL 의 3306 포트 오픈
→ 외부에서 사용할 수 있도록 연결

</br></br></br></br></br></br></br>

## Dokerfile

**Docker Image 생성을 위한 스크립트 파일** (이미지 생성과정 기술)
```docker
FROM mysql:5.7

ENV MYSQL_ALLOW_EMPTY_PASSWORD true
ENV MYSQL_DATABASE bookmarkdb

ADD ../db_mount /var/lib/bookmarkdb

EXPOSE 3306

CMD ["mysqld"]
```

- 자체 DSL(Domain-Specific-language) 언어 사용
- 파일명 자체가 Dokerfile로 생성
- **From | 어떤 서버로 부터 이미지를 만들건지**
- **ENV | 환경변수**
- **ADD | 로컬 파일을 이미지에 저장**
- **EXPOSE | 외부에 공개될 수 있는 포트**

</br>

## Docker 컨테이너 명령어
```docker
$ docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG..]
```
버전에 따라 이미지를 업로드하기 위해 `:16.02` 와 같이 태그(`:TAG`)를 붙인다. 이미지명은 중복될 수 없음

**예시**
```docker
$ docker run ubuntu:16.04
$ docker run -d -p 13306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=true --name mariadb mariadb
```

</br>

| 옵션 | 설명 |
| ----------- | ----------- |
| `-d` | detached mode 백드라운드 모드 </br>→ 일반적으로 Docker 컨테이너를 실행하면, 해당 컨테이너의 로그 출력이 터미널에 직접 표시되고, 컨테이너가 종료되면 터미널 세션도 함께 종료된다. 하지만 -d 옵션을 사용하면, **Docker 컨테이너는 백그라운드에서 독립적으로 실행되며, 터미널 세션과는 독립적으로 동작한다.** |
| `-p` | 호스트(windows, Mac OS)와 컨테이너의 포트를 연결(포워딩)  </br>-p 옵션 뒤에 {호스트 포트}:{컨테이너 포트} 형식 → `-p 8080:80` </br>**호스트 컴퓨터의 네트워크를 통해 Docker 컨테이너 서비스에 접근 가능하다. 만약 호스트 컴퓨터의 웹브라우저에서 localhost:8080 으로 접속하면 Docker 컨테이너의 웹서버에 접근 가능.** |
| `-v` | 호스트와 컨테이너의 디렉토리를 연결(마운트) 해 파일을 공유  </br>-v 옵션 뒤에 {호스트 디렉토리}:{컨테이너 디렉토리} 형식 → `-v /desktop/user/data:/data`  </br>/**desktop/user/data 디렉토리가 Docker 컨테이너에 연결** |
| `-e` | 컨테이너 내에서 사용할 환경변수 설정  </br>-e 옵션 뒤에 <변수명>=<값> 형식 → `-e MY_VARIABLE=my_value` </br>Docker 컨테이너 내부에서 `MY_VARIABLE`라는 이름의 환경 변수가 `my_value`라는 값을 가지게 됨 |
| `--name` | 컨테이너 이름 설정 </br>Docker 컨테이너의 이름을 my_container 설정 → `--name my_container` </br>`docker ps`, `docker stop`, `docker start` 등의 명령에서 컨테이너 ID 대신 사용 가능 |
| `-rm` | 프로세스 종료 시 컨테이너 자동 제거 |
| `-it` |  Interactive Mode를 의미하며 사용자가 도커 컨테이너에 직접 명령을 실행하고 결과를 확인할 수 있다. 로컬 컴퓨터에서 직접 명령을 실행하는 것 처럼 Docker 컨테이너를 제어 가능. </br>-i 와 -t를 동시에 사용한 것으로 터미널 입력을 위한 옵션 |
| `exec` | 실행중인 컨테이너에 어떤 커맨드를 전달할 때 사용 </br>`$ docker exec -it mysql bash` |
|`-link`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; | 컨테이너 연결 [컨테이너명:별칭] |

</br>

```docker
## 도커 이미지 조회
$ docker image ls
$ docker images

## 실행중인 컨테이너 조회
$ docker container ls
## 종료된 컨테이너 조회 
$ docker container ls -a

CONTAINER ID   IMAGE     COMMAND       CREATED          STATUS                      PORTS     NAMES
f6a54f6e9fe9   ubuntu    "/bin/bash"   46 seconds ago   Exited (0) 45 seconds ago             keen_panini


## 특정 키워드로 도커 이미지 조회 (windows)
$ docker images | findstr {이미지명}
$ docker images | findstr ubuntu

## 컨테이너 삭제
$ docker container rm {CONTAINER ID}
$ docker container rm {f6a54f6e9fe9}
```

</br>

### 도커로 mariadb 컨테이너 접속하기

```docker
## 백그라운드 모드로 host 포트는 13306:docker 포트는 3306으로 mariadb에 컨테이너 생성+실행
$ docker run -d -p 13306:3306 -e MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=true --name my_mariadb mariadb

## 실행 log 확인
$ docker logs {container name}
$ docker logs my_mariadb 

## mariadb container 접속
$ docker exec -it {container name} /bin/bash
$ docker exec -it my_mariadb /bin/bash
```
</br>

### Dockerfile 을 통해 컨테이너 빌드하기

```docker
## 빌드하기 userID는 docker Hub 계정의 username과 일치하도록 한다.
$ docker build -t {userID/컨테이너명:태그} .
$ docker build -t yeahdy/users-service:1.0 .
```

![7](https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/2d73e4d0-3c72-456b-811c-5d9071874675)

```docker
## Docker Hub에 올리기
$ docker push {이미지명}
$ docker push yeahdy/users-service:1.0

## Docker Hub에서 내려받기
$ docker pull {이미지명}
$ docker pull yeahdy/users-service:1.0

## Docker image명 변경 (동일한 이미지ID를 가진 2개의 이미지가 생성됨)
$ docker image tag {수정할 이미지명} {변경할 이미지명}
$ docker image tag yy123/users-service:1.0 yeahdy/users-service:1.0
```
</br>

### Docker 삭제 명령어

```docker

## Docker image 삭제
$ docker rmi {이미지명}
## 동일한 이미지ID가 여러개일 경우
$ docker rmi -f 6d6b3dcfab72

## 불필요한 리소스 삭제(중지된 컨테이너, 불필요한 네트워크, 이전단계의 이미지, 캐시)
$ docker system prune

WARNING! This will remove:
  - all stopped containers
  - all networks not used by at least one container
  - all dangling images
  - unused build cache

Are you sure you want to continue? [y/N] Y
```
