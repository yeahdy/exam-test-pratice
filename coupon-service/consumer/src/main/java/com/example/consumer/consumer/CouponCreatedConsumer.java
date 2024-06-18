package com.example.consumer.consumer;

import com.example.consumer.domain.IssueCouponEntity;
import com.example.consumer.repository.IssueCouponRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

    private final IssueCouponRepository issueCouponRepository;

    @KafkaListener(topics = "coupon-create", groupId = "coupon-group")
    public void createCouponListener(String userId) {
        issueCouponRepository.save(IssueCouponEntity.builder()
                .couponId(RandomStringUtils.randomAlphabetic(10))
                .userId(userId)
                .build());
    }

}
