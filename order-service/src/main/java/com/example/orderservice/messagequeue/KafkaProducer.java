package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderDto sendOrder(String topic, OrderDto orderDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderData = "";
        try{
            orderData = objectMapper.writeValueAsString(orderDto);
        }catch (JsonProcessingException jpe){
            log.error(jpe.getOriginalMessage());
        }
        kafkaTemplate.send(topic, orderData);
        log.info("Kafka Producer sent data from the Order microservice :: "+orderDto);
        return orderDto;
    }

}
