package com.example.userservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4JConfig {

    /**
     * CircuitBreaker 커스텀
    * */
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration(){
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(setCircuitBreakerConfig())
                .timeLimiterConfig(setTimeLimiterConfig())
                .build()
        );
    }

    private CircuitBreakerConfig setCircuitBreakerConfig(){
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .maxWaitDurationInHalfOpenState(Duration.ofMillis(3000))
                .slidingWindowType(SlidingWindowType.COUNT_BASED) //default
                .slidingWindowSize(3)   //마지막에 3번의 카운트 저장
                .build();
    }

    private TimeLimiterConfig setTimeLimiterConfig(){
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(3))
                .build();
    }

}
