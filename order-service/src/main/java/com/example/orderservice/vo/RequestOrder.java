package com.example.orderservice.vo;


import lombok.Data;

@Data
public class RequestOrder {
    private String bookId;
    private Integer qty;
    private Integer price;
}
