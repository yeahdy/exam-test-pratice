package com.example.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String bookId;
    private Integer qty;
    private Integer price;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
