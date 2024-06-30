package com.example.firstservice.service;

import com.example.firstservice.domain.FailedExamEntity;
import com.example.firstservice.domain.PassedExamEntity;
import com.example.firstservice.dto.StudentScoreDto;
import com.example.firstservice.repository.FailedExamRepository;
import com.example.firstservice.repository.PassedExamRepository;
import com.example.firstservice.repository.StudentScoreRepository;
import com.example.firstservice.vo.response.ResponseFailedExamStudent;
import com.example.firstservice.vo.response.ResponsePassedExamStudent;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceTest {

    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private PassedExamRepository passedExamRepository;
    private FailedExamRepository failedExamRepository;

    @BeforeEach
    public void init(){
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        passedExamRepository = Mockito.mock(PassedExamRepository.class);
        failedExamRepository = Mockito.mock(FailedExamRepository.class);

        studentScoreService = new StudentScoreService(
                studentScoreRepository, passedExamRepository, failedExamRepository
        );
    }


    @Test
    @DisplayName("시험 점수 저장하기")
    void save_score_mock_test(){
        //given
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

    @Test
    @DisplayName("특정 시험에 대한 합격자 명단 리스트 조회하기")
    void get_passed_exam_students_list_test(){
        //given
        PassedExamEntity finalExam1 = PassedExamEntity.builder().exam("기말고사").studentName("ellie").avgScore(83.6).build();
        PassedExamEntity finalExam2 = PassedExamEntity.builder().exam("기말고사").studentName("lie").avgScore(63.9).build();
        PassedExamEntity middleExam = PassedExamEntity.builder().exam("중간고사").studentName("elephant").avgScore(77.3).build();
        Mockito.when(passedExamRepository.findAll()).thenReturn(
                List.of(finalExam1, finalExam2 ,middleExam));

        //when
        String exam = "기말고사";
        var expectResponse = List.of(finalExam1, finalExam2)
                .stream()
                .map(passedExam -> new ResponsePassedExamStudent(passedExam.getStudentName(), passedExam.getAvgScore()))
                .toList();
        List<ResponsePassedExamStudent> passedExamStudentsList = studentScoreService.getPassedExamStudentsList(exam);

        //then
        Assertions.assertIterableEquals(expectResponse, passedExamStudentsList);
    }

    @Test
    @DisplayName("특정 시험에 대한 불합격자 명단 리스트 조회하기")
    void get_failed_exam_students_list_test(){
        //given
        FailedExamEntity finalExam1 = FailedExamEntity.builder().exam("기말고사").studentName("ellie").avgScore(56.6).build();
        FailedExamEntity finalExam2 = FailedExamEntity.builder().exam("기말고사").studentName("lie").avgScore(33.9).build();
        FailedExamEntity middleExam = FailedExamEntity.builder().exam("중간고사").studentName("elephant").avgScore(47.3).build();
        Mockito.when(failedExamRepository.findAll()).thenReturn(
                List.of(finalExam1, finalExam2 ,middleExam));

        //when
        String exam = "기말고사";
        var expectResponse = List.of(finalExam1, finalExam2)
                .stream()
                .map(passedExam -> new ResponseFailedExamStudent(passedExam.getStudentName(), passedExam.getAvgScore()))
                .toList();
        List<ResponseFailedExamStudent> failedExamStudentsList = studentScoreService.getFailedExamStudentsList(exam);

        //then
        Assertions.assertIterableEquals(expectResponse, failedExamStudentsList);
    }

}
