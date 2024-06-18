package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public void apply(Long userId){
        long count = couponCountRepository.increment();
        if(count > 100){
            return;
        }
        //kafka를 통해 consumer에서 회원의 쿠폰을 발급하도록 변경
        couponCreateProducer.createCoupon(userId);
//        couponRepository.save(new Coupon(userId));
    }

}
