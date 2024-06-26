package com.example.orderservice.service;

import com.example.orderservice.domain.OrderDetailEntity;
import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.dto.OrderDetailDto;
import com.example.orderservice.repository.OrderDetailRepository;
import com.example.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void updateOrderDetail(Long orderId, OrderDetailDto orderDetailDto) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException(String.format("%d Not found", orderId))
        );
        OrderDetailEntity orderDetailEntity = orderEntity.getOrderDetailEntity();   // N+1 문제 발생x Join으로 가져옴
        orderDetailEntity.updateOrderDetail(
                orderDetailDto.getName(),
                orderDetailDto.getPhoneNumber(),
                orderDetailDto.getEmail(),
                orderDetailDto.getAddress1(),
                orderDetailDto.getAddress2(),
                orderDetailDto.getPostalCode());
    }

    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.save(OrderDetailEntity.builder()
                .order(orderDetailDto.getOrder())
                .name(orderDetailDto.getName())
                .phoneNumber(orderDetailDto.getPhoneNumber())
                .email(orderDetailDto.getEmail())
                .address1(orderDetailDto.getAddress1())
                .address2(orderDetailDto.getAddress2())
                .postalCode(orderDetailDto.getPostalCode())
                .build());

        return OrderDetailDto.toDto(orderDetailEntity);
    }

}
