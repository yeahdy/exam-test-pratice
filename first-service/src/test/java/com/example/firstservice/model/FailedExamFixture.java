package com.example.firstservice.model;

import com.example.firstservice.domain.FailedExamEntity;
import com.example.firstservice.domain.StudentScoreEntity;
import com.example.firstservice.utils.MyCalculator;

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
