package com.example.userservice.service;

import com.example.userservice.common.response.ResponseMessage;
import com.example.userservice.common.url.OrderUrl;
import com.example.userservice.config.ServiceProperties;
import com.example.userservice.vo.ResponseOrder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;

    public List<ResponseOrder>  getUserOrderList(String userId){
        OrderUrl orderUrl = serviceProperties.getOrder();
        String url = orderUrl.getUrl() + String.format(orderUrl.getGetOrders(), userId);
        ResponseEntity<ResponseMessage<List<ResponseOrder>>> responseResult = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        ResponseMessage<List<ResponseOrder>> responseMessage = responseResult.getBody();
        return responseMessage.getData();
    }

}
