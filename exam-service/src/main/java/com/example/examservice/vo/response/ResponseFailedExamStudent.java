package com.example.examservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseFailedExamStudent {
  private final String studentName;
  private final Double avgScore;
}
