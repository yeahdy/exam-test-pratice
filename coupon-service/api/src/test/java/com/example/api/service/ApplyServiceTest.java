package com.example.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.api.repository.CouponRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("쿠폰 한번만 응모하기")
    public void apply_one_coupon_test(){
        //given
        applyService.apply(1L);
        //when
        long count = couponRepository.count();
        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("쿠폰 여러분 응모하기")
    public void apply_multiple_coupon_test() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i<threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try{
                    applyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        long count = couponRepository.count();
        assertThat(count).isEqualTo(100);
    }

}
