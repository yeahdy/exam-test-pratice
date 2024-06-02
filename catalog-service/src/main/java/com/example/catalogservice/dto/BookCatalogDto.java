package com.example.catalogservice.dto;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class BookCatalogDto implements Serializable {
    private String bookId;
    private Integer qty;
    private Integer price;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
