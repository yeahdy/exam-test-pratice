package com.example.consumer.consumer;

import com.example.consumer.domain.FailedCouponEventEntity;
import com.example.consumer.repository.FailedCouponEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponValidationConsumer {

    private final FailedCouponEventRepository failedCouponEventRepository;

    @KafkaListener(topics = "${spring.kafka.topics.coupon-to-coupon}", groupId = "coupon-group",
            containerFactory = "issueCouponFactory", errorHandler = "validationErrorHandler")
    public void replyIssueCouponListener(String userId, Acknowledgment acknowledgment){
        //TODO. 쿠폰 재발급 로직 추가

        //실패한 쿠폰발급
        failedCouponEventRepository.save(
                new FailedCouponEventEntity("RANDOM_COUPON",userId));
        acknowledgment.acknowledge();
    }

}