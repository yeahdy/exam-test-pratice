package com.example.firstservice.service;

import com.example.firstservice.dto.StudentScoreDto;
import com.example.firstservice.repository.FailedExamRepository;
import com.example.firstservice.repository.PassedExamRepository;
import com.example.firstservice.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceTest {

    @Test
    @DisplayName("시험 점수 저장 mocking 테스트")
    void save_score_mock_test(){
        //given
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(PassedExamRepository.class),
                Mockito.mock(FailedExamRepository.class)
        );

        String exam = "중간고사";
        String studentName = "ellie";
        Integer korScore = 80;
        Integer englishScore = 54;
        Integer mathScore = 63;

        //when
        studentScoreService.saveScore(
                new StudentScoreDto(exam, studentName, korScore, englishScore, mathScore)
        );
    }

}
