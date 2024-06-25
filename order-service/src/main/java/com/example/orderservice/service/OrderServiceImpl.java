package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getPrice());

        OrderEntity orderEntity = modelMapper.map(orderDto, OrderEntity.class);

        orderRepository.save(orderEntity);
        OrderDto returnValue = modelMapper.map(orderEntity, OrderDto.class);

        return returnValue;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return modelMapper.map(orderEntity, OrderDto.class);
    }

    @Override
    public List<ResponseOrder> getOrdersByUserId(String userId) {
        Iterable<OrderEntity> orderEntities = orderRepository.findByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderEntities.forEach(v -> {
            result.add(modelMapper.map(v, ResponseOrder.class));
        });
        return result;
    }
}
