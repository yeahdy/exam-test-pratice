package com.example.api.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreateProducer {

    @Value("${spring.kafka.topics.user-to-coupon}")
    private String userCouponTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createCoupon(String userId){
        kafkaTemplate.send(userCouponTopic, userId);
    }

}
