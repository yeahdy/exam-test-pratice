package com.example.firstservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ResponsePassedExamStudent {
    private final String studentName;
    private final Double avgScore;
}
