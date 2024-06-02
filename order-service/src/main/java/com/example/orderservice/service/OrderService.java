package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.vo.ResponseOrder;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    List<ResponseOrder> getOrdersByUserId(String userId);
}
