package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    private String order_id;
    private String user_id;
    private String book_id;
    private int qty;
    private int price;
    private int total_price;

}
