package com.example.userservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Getter
public class MyRefreshListener implements ApplicationListener<EnvironmentChangeEvent> {
                                        //EnvironmentChangeEvent: spring cloud config에서 제공하는 환경 설정이 변경됨을 알리는 이벤트 (git 저장소)
    @Value("${server.port}")
    private String port;
    @Value("${jwt.token.expiration_time}")
    private String expirationTime;
    @Value("${jwt.token.secret}")
    private String secret;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        //EnvironmentChangeEvent가 발생했을 때 실행됨 (/actuator/refresh)
        System.out.println("changed environment :: "+event);
    }
}
