package com.example.orderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "ORDER_DETAIL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "id")
    private OrderEntity order;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address1;

    private String address2;

    @Column(nullable = false)
    private String postalCode;


    @Builder
    public OrderDetailEntity(OrderEntity order, String name, String phoneNumber, String email, String address1,
                             String address2, String postalCode) {
        this.order = order;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.postalCode = postalCode;
    }

    public void updateOrderDetail(String name, String phoneNumber, String email, String address1, String address2,
                                  String postalCode) {
        this.name = name;
        this.phoneNumber = phoneNumber == null ? this.phoneNumber : phoneNumber;
        this.email = email == null ? this.email : email;
        this.address1 = address1 == null ? this.address1 : address1;
        this.address2 = address2 == null ? this.address2 : address2;
        this.postalCode = postalCode == null ? this.postalCode : postalCode;
    }
}
