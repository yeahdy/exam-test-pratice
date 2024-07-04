package com.example.examservice.repository;

import com.example.examservice.domain.StudentScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScoreEntity, Long> {}
