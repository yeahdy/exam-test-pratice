## **Docker network**

공식문서: https://docs.docker.com/network/

### Bridge Network

호스트PC와 별도의 가상 네트워크를 만들고, 가상의 네트워크에서 컨테이너를 배치 해 사용

```docker
$ docker network create --driver bridge {브릿지 명}
$ docker network create --driver bridge bookmark-network
```

### Host Network

- 네트워크를 호스트OS로 설정 해 호스트의 네트워크 환경을 그대로 사용
- 포트 포워딩 없이 내부 어플리케이션 사용

### None Network

- 네트워크 사용 X
- IO 네트워크만 사용하고, 외부와 단절

</br>

## Docker network 생성하기

```docker
$ docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 bookmark-network
29f3604b61d2a0d9d7c8d0ed782de452d2fc950244dda8bd6042c49dca67a0
```

- **--gateway 와 --subnet 수동 IP 할당한 이유?**</br>
기본적으로 IP주소가 할당되지만, **수동으로 컨테이너에 IP address를 지정**하게 될 경우 **네트워크 충돌 이슈를 방지하고**, **사용자 정의 Docker 네트워크를 생성할 때 서브넷과 IP를 지정**해야 오류가 발생하지 않을 수 있음.

</br>

```docker
## network 조회
$ docker network ls
NETWORK ID     NAME               DRIVER    SCOPE
29f3604b61d2   bookmark-network   bridge    local
21d40a870b17   bridge             bridge    local 
391674bbd35f   host               host      local
f775046a522d   none               null      local

## network 정보 상세보기
$ docker network inspect bookmark-network
[
    {
        "Name": "**bookmark-network**",
        "Id": "29f3604b61d2a0d9d7c8d0ed782de452d2fc950244dda8bd604c42c49dca67a0",
        "Created": "2024-06-13T05:02:00.948472848Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    **"Subnet": "172.18.0.0/16"**,
                    **"Gateway": "172.18.0.1"**
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {},
        "Options": {},
        "Labels": {}
    }
]
```

- **`"Containers": {}`**</br>
현재 해당 네트워크에 포함된 컨테이너가 없지만, 모든 마이크로 서비스를 하나의 bookmark-network 안에서 작동될 수 있도록 설정하면 같은 서브넷을 사용하는 네트워크 내에 묶일 수 있다.

</br>

## **Docker 에서 네트워크를 직접 생성하는 이유**
<img src = "https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/8097e088-4268-4633-9d4f-dab4af5952f6" width="50%" height="height 50%">

- 컨테이너 == 게스트OS, 각 게스트OS 마다 고유한 IP 주소가 부여
    - 각 마이크로서비스(user-service, API Gateway, Grafana…) 들은 고유한 IP주소를 할당 받음

- 컨테이너들 간에 IP 주소, ContinerID를 이용 해서 서로 통신할 수 있다.

- 컨테이너 배포 시 비어있는 IP 주소부터 순차적으로 IP를 할당하기 때문에 **환경에 따라 설정한 IP와 다른 IP로 할당될 수도 있다.** (컨테이너 가상화 환경은 Docker 말고 Creo, ContainerD도 존재)

- 따라서 만약 user-service 의 IP주소를 172.17.0.2 설정했지만, 172.17.0.3 으로 변경될 가능성이 있음</br>
그럼 통신하는 다른 마이크로서비스에서 변경된 IP로 수정 해야 하는 번거로움이 발생!

- **이를 위해 같은 네트워크에 포함된 컨테이너들끼리 IP 주소 외에 Container이름으로 통신 가능**
    - `172.17.0.1` 네트워크 내에 묶여져 있는 `config-service`와 `mariadb` 컨테이너는 고유IP가 변경되더라도 Container 이름으로 통신이 가능하다

</br>

**Rabbitmq 기동**

```docker
$ docker run -d --name rabbitmq --network bookmark-network -p 15672:15672 -p 5672:5672 -p 15671:15671 -p 5671:5671 -p 4369:4369 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:management
```

**Config-service 기동**

```docker
$ docker run -d -p 8888:8888 --network bookmark-network -e "spring.rabbitmq.host=rabbitmq" -e "spring.profiles.active=default" --name config-service yeahdy/config-service:1.0
```
