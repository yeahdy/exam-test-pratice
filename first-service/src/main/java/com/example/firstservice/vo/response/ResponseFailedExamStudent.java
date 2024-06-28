package com.example.firstservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseFailedExamStudent {
    private final String studentName;
    private final Double avgScore;
}
