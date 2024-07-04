package com.example.examservice.model;

import com.example.examservice.domain.PassedExamEntity;
import com.example.examservice.domain.StudentScoreEntity;
import com.example.examservice.utils.MyCalculator;

public class PassedExamFixture {

  public static PassedExamEntity passed(StudentScoreEntity studentScore) {
    return PassedExamEntity.builder()
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

  public static PassedExamEntity passed(String exam, String studentName) {
    return PassedExamEntity.builder().exam(exam).studentName(studentName).avgScore(94.3).build();
  }
}
