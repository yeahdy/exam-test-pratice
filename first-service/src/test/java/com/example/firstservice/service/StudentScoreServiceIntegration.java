package com.example.firstservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstservice.IntegrationTest;
import com.example.firstservice.model.StudentScoreData;
import com.example.firstservice.repository.StudentScoreRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentScoreServiceIntegration extends IntegrationTest {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void student_score_service_integration_test(){
        var studentScoreEntity = StudentScoreData.passedFixture();
        var savedStudentScore = studentScoreRepository.save(studentScoreEntity);

        entityManager.flush();
        entityManager.clear();

        var studentScore = studentScoreRepository.findById(1L).orElseThrow();
        assertThat(savedStudentScore.getId()).isEqualTo(studentScore.getId());
        assertThat(savedStudentScore.getExam()).isEqualTo(studentScore.getExam());
        assertThat(savedStudentScore.getStudentName()).isEqualTo(studentScore.getStudentName());
    }

}
