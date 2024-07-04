package com.example.examservice.vo.request;

import com.example.examservice.dto.StudentScoreDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSaveScore {
  private String studentName;
  private Integer korScore;
  private Integer englishScore;
  private Integer mathScore;

  public StudentScoreDto toDto(String exam) {
    return new StudentScoreDto(exam, studentName, korScore, englishScore, mathScore);
  }
}
