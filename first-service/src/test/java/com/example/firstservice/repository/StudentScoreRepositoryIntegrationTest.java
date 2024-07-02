package com.example.firstservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstservice.IntegrationTest;
import com.example.firstservice.model.StudentScoreData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentScoreRepositoryIntegrationTest extends IntegrationTest {

  @Autowired private StudentScoreRepository studentScoreRepository;

  @Autowired private EntityManager entityManager;

  @Test
  void student_score_service_integration_test() {
    // given
    var studentScoreEntity = StudentScoreData.passedFixture();

    // when
    var savedStudentScore = studentScoreRepository.save(studentScoreEntity);
    entityManager.flush();
    entityManager.clear();

    // then
    var studentScore = studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();
    assertThat(studentScore.getId()).isEqualTo(savedStudentScore.getId());
    assertThat(studentScore.getExam()).isEqualTo(savedStudentScore.getExam());
    assertThat(studentScore.getStudentName()).isEqualTo(savedStudentScore.getStudentName());
  }
}
