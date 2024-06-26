package com.example.orderservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.orderservice.domain.OrderDetailEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @DisplayName("주문 상세 주문자이름 setter로 변경하기")
    @Transactional
    void update_order_detail_test() {
        String name = "ellie";

        System.out.println("##############findById##############");
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(1L).orElse(null);
        orderDetailEntity.setName(name);
//        System.out.println("##############save##############");
//        orderDetailRepository.save(orderDetailEntity);

        System.out.println("##############findAll##############");
        OrderDetailEntity changedOrderDetailEntity = orderDetailRepository.findAll().get(0);
        assertThat(changedOrderDetailEntity.getName()).isEqualTo(name);
    }

}
