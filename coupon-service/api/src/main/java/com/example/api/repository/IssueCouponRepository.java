package com.example.api.repository;

import com.example.api.domain.IssueCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueCouponRepository extends JpaRepository<IssueCouponEntity,Long> {
}
