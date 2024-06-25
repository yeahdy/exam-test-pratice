package com.example.orderservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.dto.OrderDetailDto;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderDetailServiceTest {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    @DisplayName("회원의 주문을 저장한 후 주문 상세도 함께 저장한다.")
    void create_order_test(){
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setBookId("CATALOG-004");
        orderDto.setQty(3);
        orderDto.setPrice(21600);
        orderDto.setUserId("3fc20e9c-6c16-47f8-a2cc-06d5293bc866");

        OrderDto order = orderService.createOrder(orderDto);
        OrderEntity orderEntity = orderRepository.findByOrderId(order.getOrderId());

        //when
        String name = "ellie";
        OrderDetailDto orderDetailDto = orderDetailService.createOrderDetail(
                new OrderDetailDto(null, orderEntity,name,"01012345678","test@gmail.com","주소1","주소2","1234")
        );

        //then
        assertThat(orderDetailDto.getOrder().getId()).isEqualTo(orderEntity.getId());
        assertThat(orderDetailDto.getName()).isEqualTo(name);
    }

}
