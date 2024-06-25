package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDetailDto;

public interface OrderDetailService {

    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);

    void updateOrderDetail(Long orderId, OrderDetailDto orderDetailDto);

}
