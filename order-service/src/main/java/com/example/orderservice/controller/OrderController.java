package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.response.ResponseMessage;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import java.util.List;
import java.util.Random;
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

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseMessage<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {
        log.info("Before add orders data");
        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        /* jpa */
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = modelMapper.map(createdOrder, ResponseOrder.class);

        log.info("After added orders data");
        return ResponseMessage.createSuccess(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseMessage<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) throws Exception {
        log.info("Before retrieve orders data");
        List<ResponseOrder> orderList = orderService.getOrdersByUserId(userId);

        try {
            Random rnd = new Random();
            int value = rnd.nextInt(5);
            if (value % 2 == 0) {
                Thread.sleep(10000);
                throw new Exception("장애 발생");
            }
        } catch(InterruptedException ex) {
            log.warn(ex.getMessage());
        }

        log.info("Add retrieved orders data");
        return ResponseMessage.success(orderList,"Order Enquiry");
    }
}
