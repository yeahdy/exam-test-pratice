package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

    Environment env;

    @Autowired
    public SecondServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Second Service message";
    }

    @GetMapping("/check")
    public String check() {
        log.info("spring.cloud.client.hostname={}", env.getProperty("spring.cloud.client.hostname"));
        log.info("spring.cloud.client.ip-address={}", env.getProperty("spring.cloud.client.ip-address"));

        // 여러개의 인스턴스 실행 후 외부에서 API 호출 시 gateway를 통해 로드밸런싱(부하분산)이 가능하여 인스턴스에 요청이 나뉘어서 들어오게 된다.
        return String.format("This is a message from Second Service on PORT %s"
                , env.getProperty("local.server.port"));
    }
}
