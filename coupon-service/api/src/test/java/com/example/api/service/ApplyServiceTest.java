package com.example.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void apply_one_coupon_test(){
        //given
        applyService.apply(1L);
        //when
        long count = couponRepository.count();
        //then
        assertThat(count).isEqualTo(1);
    }

}
