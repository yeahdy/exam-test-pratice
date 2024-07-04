package com.example.examservice.dto;

import com.example.examservice.domain.StudentScoreEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentScoreDto {

  @Column(name = "EXAM", nullable = false)
  private String exam;

  @Column(name = "STUDENT_NAME", nullable = false)
  private String studentName;

  @Column(name = "KOR_SCORE", nullable = false)
  private Integer korScore;

  @Column(name = "ENGLISH_SCORE", nullable = false)
  private Integer englishScore;

  @Column(name = "MATH_SCORE", nullable = false)
  private Integer mathScore;

  public StudentScoreEntity toEntity() {
    return StudentScoreEntity.builder()
        .exam(exam)
        .studentName(studentName)
        .korScore(korScore)
        .englishScore(englishScore)
        .mathScore(mathScore)
        .build();
  }
}
