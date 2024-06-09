## Kafka 다운로드

> **Kafka 실행**
> 
> ```bash
> docker run --env CONFIG_NAME=CONFIG_VALUE -p 9092:9092 apache/kafka:3.7.0
> ```
> 
> ### **Kafka와 데이터를 주고받기 위해 사용하는 Java Library**
> - https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients

</br>

### **Zookeeper 및 Kafka 서버 기동**
> ```bash
> ${KAFKA_HOME\bin\windows} .\zookeeper-server-start.bat ..\..\config\zookeeper.properties
> ```
> ```bash
> ${KAFKA_HOME\bin\windows} .\kafka-server-start.bat ..\..\config\server.properties
> ```

</br>

### **Topic 생성**

> ```bash
> ${KAFKA_HOME\bin\windows} .\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic first-events --partitions 1
> ```
- **kafka-topics.sh**
토픽 프로그램 실행
- **-create --topic {토픽명}**
토픽 생성과 토픽이름 지정 (quickstart-events)
- **--bootstrap-server localhost:9092**
카프카 서버 (포트번호 9092)에 토픽을 생성
- **--partitions 1**
멀티 클러스트링을 구성했을 때 카프카의 토픽에 전달되어 있는 메세지를 몇 군데 나눠서 저장할 것인지 (서비스를 1대만 구성할 것이기 때문에 1)

</br>

### **Topic 목록 확인**

> ```bash
> ${KAFKA_HOME/bin/windows} ./kafka-topics.bat --bootstrap-server localhost:9092 --list
> ```

```bash
PS C:{HOME}\springcloud\kafka_demo\kafka_2.13-3.7.0\bin\windows> .\kafka-topics.bat --bootstrap-server localhost:9092 --describe
Topic: first-events     TopicId: MYZc27YVSWSea0QC3XZgxA PartitionCount: 1  ReplicationFactor: 1     Configs:
        Topic: first-events     Partition: 0    Leader: 0       Replicas: 0Isr: 0
```

</br>

### **Topic 정보 상세 확인**

> ```bash
> ${KAFKA_HOME/bin/windows} ./kafka-topics.bat --describe --topic quickstart-events --bootstrap-server localhost:9092
> ```

</br>

### **Windows에서 기동**

> ```bash
> ${KAFKA_HOME\bin\windows} .\zookeeper-server-start.bat .\config\zookeeper.properties
> ```

</br>

### **메시지 생산 (보내기)**

> ```bash
> ${KAFKA_HOME/bin/windows} ./kafka-console-producer.bat --broker-list localhost:9092 --topic quickstart-events
> ```
- **-topic {topic명}**
메세지 보낼 토픽명 명시

</br>

**Topic으로 메세지 전달**

1. 프로듀서에서 메세지 전달 시 Topic에 데이터 저장
2. 컨슈머가 해당 토픽을 구독(subscribe)
3. 토픽에 전달된 내용물이 있을 때 메세지를 컨슈머에게 일괄 전송 

</br>

### **메시지 소비 (받기)**

> ```bash
> ${KAFKA_HOME/bin/windows} ./kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic quickstart-events --from-beginning
> ```
- **--topic {topic명} --from-beginning**
 명시한 topic 에서 처음부터 메세지 받기

</br>

---

## Kafka Client
![1_KafkaClient](https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/44e9910f-70b2-4e27-ac4c-72123620e346)

중간에 Kafka Cluster 시스템을 두고

메세지를 보내는 쪽에서는 Kafka 에 전달 → `Kafka Cluster`

`Kafka Cluster` → 메세지를 받는 쪽은 누가 생성하고, 전달했는지 알 필요 없이 단순히 메세지 자체로만 생성/전달 

</br>

## Kafka Connect
![2_KafkaConnect](https://github.com/yeahdy/spring-cloud-pratice/assets/86579541/db6f7367-15ad-464c-84c9-f5b43cc96cf3)

특정한 데이터를 가져와서 Kafka 클러스터에 저장할 때 **Kafka Connect Source** 사용

1. **Kafka Connect Source**는 **데이터를 받는 역할.** 소스(데이터베이스 파일, orcle데이터 등…)를 저장
2. **Kafka Connect Sink**는 **데이터를 보내는 역할**로 데이터를 export해서 Target(S3, mysql 데이터)에 전달

</br>

---

## Kafka Connect 명령어

### **Kafka Connect 설치**

```bash
wget http://packages.confluent.io/archive/7.1/confluent-community-7.1.0.tar.gz
tar xvf confluent-community-7.1.0.tar.gz
```

### **Kafka Connect 실행**

```bash
# 둘중 하나 사용
${KAFKA_HOME} ./config/connect-distributed.properties
${KAFKA_CONNECT_HOME/bin/windows} ./connect-distributed.bat ../../etc/kafka/connect-distributed.properties
```

Zookeeper, Kafka 서버 실행중일 때 가능

### **Kafka Connect 목록확인**

```bash
${KAFKA_CONNECT_HOME/bin/windows} ./kafka-topics.bat --bootstrap-server localhost:9092 --list
```

</br>

## 포스트맨으로 Kafka Connect 에 메세지 전달/조회

### **전달 (POST)**

- **127.0.0.1:8083/connectors**

**Source Connect 전달**

```json
{
    "name" : "my-source-connect",
    "config" : {
        "connector.class" : "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url":"jdbc:mariadb://localhost:3306/testdb",
        "connection.user":"root",
        "connection.password":"test",
        "mode": "incrementing",
        "incrementing.column.name" : "id",
        "table.whitelist":"testdb.user",
        "topic.prefix" : "my_topic_",
        "tasks.max" : "1"
    }
}
```

- 위 데이터를 소스 커넥터에 전달했기 때문에
사용자가 `"testdb.user"`테이블에 데이터 추가 시 소스 커넥터에 데이터가 쌓이게 됨

**Sink Connect 전달**

```json
{
    "name":"my-sink-connect",
    "config":{
        "connector.class" : "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url":"jdbc:mariadb://localhost:3306/testdb",
        "connection.user":"root",
        "connection.password":"test",
        "auto.create":"true",
        "auto.evolve":"true",
        "delete.enabled":"false",
        "tasks.max":"1",
        "topics":"my_topic_user"
    }
}
```

- `auto.create":"true"`
topics로 지정한 이름과 같은 테이블 생성
- Sink Connect를 생성함과 동시에 연결한 DB에 `my_topic_user`테이블 생성
- `my_topic_user`**테이블 스키마?**
이전에 Topic에 샘플데이터를 추가 해 놓아서 Topic에 저장된 데이터를 가지고 가서 해당 데이터구조를 토대로 스키마 생성

</br>

### **조회 (GET)**

- 127.0.0.1:8083/connectors
- 127.0.0.1:8083/connectors/my-source-connect/status
