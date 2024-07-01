package com.example.firstservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstservice.IntegrationTest;
import com.example.firstservice.dto.StudentScoreDto;
import com.example.firstservice.model.StudentScoreData;
import com.example.firstservice.utils.MyCalculator;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentScoreServiceIntegrationTest  extends IntegrationTest {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("시험 점수 도달한 합격자 명단 조회하기")
    void save_passed_student_score_test(){
        //given
        var studentScore = StudentScoreData.passedFixture();

        //when
        studentScoreService.saveScore(
                new StudentScoreDto(studentScore.getExam(),
                        studentScore.getStudentName(),
                        studentScore.getKorScore(),
                        studentScore.getEnglishScore(),
                        studentScore.getMathScore()));
        entityManager.flush();
        entityManager.clear();

        //then
        var passedStudentResponseList = studentScoreService.getPassedExamStudentsList(studentScore.getExam());
        assertThat(passedStudentResponseList.size()).isEqualTo(1);

        var passedStudentResponse = passedStudentResponseList.get(0);
        assertThat(passedStudentResponse.getAvgScore()).isEqualTo(
                new MyCalculator(0.0)
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .add(studentScore.getMathScore().doubleValue())
                        .divid(3.0)
                        .getResult()
        );
    }

    @Test
    @DisplayName("시험 점수 미달인 불합격자 명단 조회하기")
    void save_failed_student_score_test(){
        //given
        var studentScore = StudentScoreData.failedFixture();

        //when
        studentScoreService.saveScore(
                new StudentScoreDto(studentScore.getExam(),
                        studentScore.getStudentName(),
                        studentScore.getKorScore(),
                        studentScore.getEnglishScore(),
                        studentScore.getMathScore()));
        entityManager.flush();
        entityManager.clear();

        //then
        var failedStudentResponseList = studentScoreService.getFailedExamStudentsList(studentScore.getExam());
        assertThat(failedStudentResponseList.size()).isEqualTo(1);

        var failedStudentResponse = failedStudentResponseList.get(0);
        assertThat(failedStudentResponse.getAvgScore()).isEqualTo(
                new MyCalculator(0.0)
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .add(studentScore.getMathScore().doubleValue())
                        .divid(3.0)
                        .getResult()
        );
    }

}
