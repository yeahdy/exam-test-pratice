package com.example.firstservice.repository;

import com.example.firstservice.domain.FailedExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedExamRepository extends JpaRepository<FailedExamEntity,Long> {
}
