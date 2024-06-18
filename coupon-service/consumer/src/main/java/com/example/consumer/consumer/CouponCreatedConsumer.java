package com.example.consumer.consumer;

import com.example.consumer.domain.IssueCouponEntity;
import com.example.consumer.repository.IssueCouponRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

    private final IssueCouponRepository issueCouponRepository;

    @KafkaListener(topics = "coupon-create", groupId = "coupon-group",
            containerFactory = "issueCouponFactory", errorHandler = "validationErrorHandler")
    @SendTo(value = {"reply-coupon"})
    public void createCouponListener(String userId, Acknowledgment acknowledgment) {
        issueCouponRepository.save(IssueCouponEntity.builder()
                .couponId(RandomStringUtils.randomAlphabetic(10))
                .userId(userId)
                .build());
        acknowledgment.acknowledge();
    }

}
