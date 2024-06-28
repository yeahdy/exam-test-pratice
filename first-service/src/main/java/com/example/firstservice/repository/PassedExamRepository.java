package com.example.firstservice.repository;

import com.example.firstservice.domain.PassedExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassedExamRepository extends JpaRepository<PassedExamEntity,Long> {
}
