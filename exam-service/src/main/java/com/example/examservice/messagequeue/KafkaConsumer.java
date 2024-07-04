package com.example.examservice.messagequeue;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumer {

  private final KafkaConsumerService kafkaConsumerService;

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("score-topic").build();
  }

  @KafkaListener(id = "score-id", topics = "score-topic")
  public void listen(String message) {
    kafkaConsumerService.process(message);
  }
}
