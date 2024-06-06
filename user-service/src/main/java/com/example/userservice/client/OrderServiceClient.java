package com.example.userservice.client;

import com.example.userservice.common.response.ResponseMessage;
import com.example.userservice.vo.ResponseOrder;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders")
    ResponseMessage<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId);

}
