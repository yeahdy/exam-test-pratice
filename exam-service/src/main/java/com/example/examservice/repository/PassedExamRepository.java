package com.example.examservice.repository;

import com.example.examservice.domain.PassedExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedExamRepository extends JpaRepository<PassedExamEntity, Long> {}
