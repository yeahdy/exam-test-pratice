package com.example.userservice.vo;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseOrder {
    private String bookId;
    private Integer qty;
    private Integer price;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
