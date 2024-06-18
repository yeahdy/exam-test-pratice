package com.example.consumer.repository;

import com.example.consumer.domain.IssueCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCouponRepository extends JpaRepository<IssueCouponEntity,Long> {
}
