package com.example.examservice.model;

import com.example.examservice.domain.FailedExamEntity;
import com.example.examservice.domain.StudentScoreEntity;
import com.example.examservice.utils.MyCalculator;

public class FailedExamFixture {

  public static FailedExamEntity failed(StudentScoreEntity studentScore) {
    return FailedExamEntity.builder()
        .exam(studentScore.getExam())
        .studentName(studentScore.getStudentName())
        .avgScore(
            new MyCalculator(0.0)
                .add(studentScore.getKorScore().doubleValue())
                .add(studentScore.getEnglishScore().doubleValue())
                .add(studentScore.getMathScore().doubleValue())
                .divid(3.0)
                .getResult())
        .build();
  }

  public static FailedExamEntity failed(String exam, String studentName) {
    return FailedExamEntity.builder().exam(exam).studentName(studentName).avgScore(47.3).build();
  }
}
