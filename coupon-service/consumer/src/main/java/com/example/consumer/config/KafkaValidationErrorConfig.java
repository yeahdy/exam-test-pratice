package com.example.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Slf4j
@EnableKafka
@Configuration
public class KafkaValidationErrorConfig {

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler() {
        return (message, exception) -> {
            log.error("validation error handler message={} exception groupId={} exception cause={}",
                    message.getPayload(), exception.getGroupId(), exception.getRootCause());
            return message; //return 값이 topic에 write
        };
    }

}