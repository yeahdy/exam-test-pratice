package com.example.firstservice.vo.request;

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
}
