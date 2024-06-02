package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class ResponseBookCatalog {
    private String bookId;
    private String bookName;
    private Integer stock;
    private Integer price;
    private Date createdAt;
}
