package com.example.consumer.repository;

import com.example.consumer.domain.FailedCouponEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedCouponEventRepository extends JpaRepository<FailedCouponEventEntity, Long> {
}
