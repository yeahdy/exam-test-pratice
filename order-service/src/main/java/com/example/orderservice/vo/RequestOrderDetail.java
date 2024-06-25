package com.example.orderservice.vo;


import lombok.Getter;

@Getter
public class RequestOrderDetail {

    private String name;
    private Integer phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String postalCode;
}
