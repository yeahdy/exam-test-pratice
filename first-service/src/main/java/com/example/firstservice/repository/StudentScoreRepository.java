package com.example.firstservice.repository;

import com.example.firstservice.domain.StudentScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScoreEntity, Long> {
}
