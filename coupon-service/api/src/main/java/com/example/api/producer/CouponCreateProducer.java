package com.example.api.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreateProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createCoupon(String userId){
        kafkaTemplate.send("coupon-create", userId);
    }

}
