package com.example.orderservice.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderDto implements Serializable {
    private String bookId;
    private Integer qty;
    private Integer price;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    public void calcOrderPrice(){
        this.orderId = UUID.randomUUID().toString();
        this.totalPrice = this.qty * this.price;
    }

}
