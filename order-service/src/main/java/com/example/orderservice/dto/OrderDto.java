package com.example.orderservice.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class OrderDto implements Serializable {
    private String bookId;
    private Integer qty;
    private Integer price;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
