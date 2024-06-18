package com.example.api.service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponApplyService {
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public void apply(String userId){
        Long appliedCoupon = appliedUserRepository.addCoupon(userId);
        if(appliedCoupon != 1){
            return;
        }

        long count = couponCountRepository.increment();
        if(count > 100){
            return;
        }
        //TODO. user 서비스에서 userId를 가져오도록 추가

        //kafka를 통해 consumer에서 회원의 쿠폰을 발급
        couponCreateProducer.createCoupon(userId);
    }

}
