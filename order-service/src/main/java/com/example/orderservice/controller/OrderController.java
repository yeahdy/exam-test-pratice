package com.example.orderservice.controller;

import static com.example.orderservice.messagequeue.KafkaTopics.BOOK_CATALOG_TOPIC;
import static com.example.orderservice.messagequeue.KafkaTopics.ORDERS_BOOK;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.response.ResponseMessage;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
    private final Environment env;
    private final  OrderService orderService;
    private final  ModelMapper modelMapper;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;


    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseMessage<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {
        log.info("Before add order data");
        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);

//        OrderDto createdOrder = orderService.createOrder(orderDto);
//        ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);

        orderDto.calcOrderPrice();
        // 주문 내역 kafka Source connect 전달 -> kafka producer
        orderProducer.sendOrder(ORDERS_BOOK,orderDto);
        // 주문 내역 kafka producer 전달
        kafkaProducer.sendOrder(BOOK_CATALOG_TOPIC,orderDto);
        ResponseOrder responseOrder = modelMapper.map(orderDto, ResponseOrder.class);
        log.info("After added order data");
        return ResponseMessage.createSuccess(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseMessage<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {
        log.info("Before retrieve orders data");
        List<ResponseOrder> orderList = orderService.getOrdersByUserId(userId);
        return ResponseMessage.success(orderList,"Order Enquiry");
    }
}
