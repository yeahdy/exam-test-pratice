package com.example.orderservice.dto;

import com.example.orderservice.domain.OrderDetailEntity;
import com.example.orderservice.domain.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private Long id;
    private OrderEntity order;
    private String name;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String postalCode;

    public static OrderDetailDto toDto(OrderDetailEntity orderDetailEntity) {
        return OrderDetailDto.builder()
                .id(orderDetailEntity.getId())
                .order(orderDetailEntity.getOrder())
                .name(orderDetailEntity.getName())
                .phoneNumber(orderDetailEntity.getPhoneNumber())
                .email(orderDetailEntity.getEmail())
                .address1(orderDetailEntity.getAddress1())
                .address2(orderDetailEntity.getAddress2())
                .postalCode(orderDetailEntity.getPostalCode())
                .build();
    }

}
