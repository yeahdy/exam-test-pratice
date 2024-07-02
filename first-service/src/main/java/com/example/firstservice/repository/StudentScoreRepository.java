package com.example.firstservice.repository;

import com.example.firstservice.domain.StudentScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScoreEntity, Long> {}
