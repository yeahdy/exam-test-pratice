package com.example.firstservice;

import jakarta.transaction.Transactional;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@SpringBootTest
@Ignore //상속용으로 만든 부모클래스로 Test가 실행되지 않아도 됨
@Transactional
@ContextConfiguration(initializers = IntegrationTest.IntegrationTestInitializer.class)
public class IntegrationTest {

    static DockerComposeContainer rdbms;

    static {
        rdbms = new DockerComposeContainer(new File("infra/test/docker-compose.yaml"))
                .withExposedService(
                        "local-db",
                        3306,
                        Wait.forLogMessage(".*ready for connections.*", 1)
                                .withStartupTimeout(Duration.ofSeconds(300))
                )
                .withExposedService(
                        "local-db-migrate",
                        0,
                        Wait.forLogMessage("(.*Successfully applied.*)|(.*Successfully validated.*)",1)
                                .withStartupTimeout(Duration.ofSeconds(300))
                );

        rdbms.start();
    }

    //테스트가 동작할때 application.yaml 파일을 초기화
    static class IntegrationTestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Map<String, String> properties = new HashMap<>();

            //docker container에 올라간 DB의 host와 port 가져오기
            var rdbmsHost = rdbms.getServiceHost("local-db", 3306);
            var rdbmsPort = rdbms.getServicePort("local-db", 3306);

            //database url을 동적으로 설정해서 테스트용 DB로 연결
            properties.put("spring.datasource.url", "jdbc:mysql://"+rdbmsHost+":"+rdbmsPort+"/score");
            TestPropertyValues.of(properties)
                    .applyTo(applicationContext);
        }
    }
}
