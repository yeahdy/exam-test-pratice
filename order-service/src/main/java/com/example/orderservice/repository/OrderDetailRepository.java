package com.example.orderservice.repository;

import com.example.orderservice.domain.OrderDetailEntity;
import com.example.orderservice.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

    OrderDetailEntity findByOrder(OrderEntity order);

}
