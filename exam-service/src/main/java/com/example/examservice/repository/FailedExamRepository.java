package com.example.examservice.repository;

import com.example.examservice.domain.FailedExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedExamRepository extends JpaRepository<FailedExamEntity, Long> {}
