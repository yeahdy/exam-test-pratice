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
    @DisplayName("시험 점수 저장하기")
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

    @Test
    @DisplayName("시험 평균점수 60점 이상일 경우")
    void save_exam_passed_test(){
        //given
        StudentScoreRepository studentScoreRepository= Mockito.mock(StudentScoreRepository.class);
        PassedExamRepository passedExamRepository = Mockito.mock(PassedExamRepository.class);
        FailedExamRepository failedExamRepository = Mockito.mock(FailedExamRepository.class);
        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                passedExamRepository,
                failedExamRepository
        );

        String exam = "중간고사";
        String studentName = "ellie";
        Integer korScore = 78;
        Integer englishScore = 93;
        Integer mathScore = 83;

        //when
        studentScoreService.saveScore(
                new StudentScoreDto(exam, studentName, korScore, englishScore, mathScore)
        );

        //then
        //passedExam 테이블에는 저장되고, failedExam 테이블에는 저장이 되지 않아야 한다.
        Mockito.verify(passedExamRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(failedExamRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("시험 평균점수 60점 미만일 경우")
    void save_exam_failed_test(){
        //given
        StudentScoreRepository studentScoreRepository= Mockito.mock(StudentScoreRepository.class);
        PassedExamRepository passedExamRepository = Mockito.mock(PassedExamRepository.class);
        FailedExamRepository failedExamRepository = Mockito.mock(FailedExamRepository.class);
        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                passedExamRepository,
                failedExamRepository
        );

        String exam = "중간고사";
        String studentName = "ellie";
        Integer korScore = 48;
        Integer englishScore = 58;
        Integer mathScore = 35;

        //when
        studentScoreService.saveScore(
                new StudentScoreDto(exam, studentName, korScore, englishScore, mathScore)
        );

        //then
        //failedExam 테이블에는 저장되고, passedExam 테이블에는 저장이 되지 않아야 한다.
        Mockito.verify(passedExamRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(failedExamRepository, Mockito.times(1)).save(Mockito.any());
    }

}
