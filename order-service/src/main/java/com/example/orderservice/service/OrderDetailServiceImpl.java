package com.example.orderservice.service;

import com.example.orderservice.domain.OrderDetailEntity;
import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.dto.OrderDetailDto;
import com.example.orderservice.repository.OrderDetailRepository;
import com.example.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;


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
