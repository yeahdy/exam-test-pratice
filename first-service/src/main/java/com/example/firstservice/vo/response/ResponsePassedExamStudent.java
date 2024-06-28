package com.example.firstservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponsePassedExamStudent {
    private final String studentName;
    private final Double avgScore;
}
