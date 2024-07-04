package com.example.examservice.service;

import com.example.examservice.domain.FailedExamEntity;
import com.example.examservice.domain.PassedExamEntity;
import com.example.examservice.domain.StudentScoreEntity;
import com.example.examservice.dto.StudentScoreDto;
import com.example.examservice.model.FailedExamFixture;
import com.example.examservice.model.PassedExamFixture;
import com.example.examservice.model.StudentScoreData;
import com.example.examservice.repository.FailedExamRepository;
import com.example.examservice.repository.PassedExamRepository;
import com.example.examservice.repository.StudentScoreRepository;
import com.example.examservice.vo.response.ResponseFailedExamStudent;
import com.example.examservice.vo.response.ResponsePassedExamStudent;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class StudentScoreServiceMockTest {

  private StudentScoreService studentScoreService;
  private StudentScoreRepository studentScoreRepository;
  private PassedExamRepository passedExamRepository;
  private FailedExamRepository failedExamRepository;

  @BeforeEach
  public void init() {
    studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
    passedExamRepository = Mockito.mock(PassedExamRepository.class);
    failedExamRepository = Mockito.mock(FailedExamRepository.class);

    studentScoreService =
        new StudentScoreService(studentScoreRepository, passedExamRepository, failedExamRepository);
  }

  @Test
  @DisplayName("시험 점수 저장하기")
  void save_score_mock_test() {
    // given
    String exam = "중간고사";
    String studentName = "ellie";
    Integer korScore = 80;
    Integer englishScore = 54;
    Integer mathScore = 63;

    // when
    studentScoreService.saveScore(
        new StudentScoreDto(exam, studentName, korScore, englishScore, mathScore));
  }

  @Test
  @DisplayName("시험 평균점수 60점 이상일 경우")
  void save_exam_passed_test() {
    // given
    StudentScoreEntity studentScore = StudentScoreData.passed().build();
    PassedExamEntity expectPassedExamEntity = PassedExamFixture.passed(studentScore);

    // when
    // ArgumentCaptor: 메서드에 전달된 인자를 캡쳐해서 전달한 인자값이 제대로 로직에서 작동하는지 검증
    ArgumentCaptor<PassedExamEntity> passedExamEntityCaptor =
        ArgumentCaptor.forClass(PassedExamEntity.class);
    studentScoreService.saveScore(
        new StudentScoreDto(
            studentScore.getExam(),
            studentScore.getStudentName(),
            studentScore.getKorScore(),
            studentScore.getEnglishScore(),
            studentScore.getMathScore()));

    // then
    // passedExam 테이블에는 저장되고, failedExam 테이블에는 저장이 되지 않아야 한다.
    Mockito.verify(passedExamRepository, Mockito.times(1)).save(passedExamEntityCaptor.capture());
    PassedExamEntity passedExamEntity = passedExamEntityCaptor.getValue();
    Assertions.assertEquals(expectPassedExamEntity.getExam(), passedExamEntity.getExam());
    Assertions.assertEquals(
        expectPassedExamEntity.getStudentName(), passedExamEntity.getStudentName());
    Assertions.assertEquals(expectPassedExamEntity.getAvgScore(), passedExamEntity.getAvgScore());

    Mockito.verify(failedExamRepository, Mockito.times(0)).save(Mockito.any());
  }

  @Test
  @DisplayName("시험 평균점수 60점 미만일 경우")
  void save_exam_failed_test() {
    // given
    StudentScoreEntity studentScore = StudentScoreData.failed().exam("FINAL").build();
    FailedExamEntity expectFailedExamEntity = FailedExamFixture.failed(studentScore);

    // when
    ArgumentCaptor<FailedExamEntity> failedExamEntityCaptor =
        ArgumentCaptor.forClass(FailedExamEntity.class);
    studentScoreService.saveScore(
        new StudentScoreDto(
            studentScore.getExam(),
            studentScore.getStudentName(),
            studentScore.getKorScore(),
            studentScore.getEnglishScore(),
            studentScore.getMathScore()));

    // then
    // failedExam 테이블에는 저장되고, passedExam 테이블에는 저장이 되지 않아야 한다.
    Mockito.verify(passedExamRepository, Mockito.times(0)).save(Mockito.any());
    Mockito.verify(failedExamRepository, Mockito.times(1)).save(failedExamEntityCaptor.capture());
    FailedExamEntity failedExamEntity = failedExamEntityCaptor.getValue();

    Assertions.assertEquals(expectFailedExamEntity.getExam(), failedExamEntity.getExam());
    Assertions.assertEquals(
        expectFailedExamEntity.getStudentName(), failedExamEntity.getStudentName());
    Assertions.assertEquals(expectFailedExamEntity.getAvgScore(), failedExamEntity.getAvgScore());
  }

  @Test
  @DisplayName("특정 시험에 대한 합격자 명단 리스트 조회하기")
  void get_passed_exam_students_list_test() {
    // given
    String exam = "기말고사";
    PassedExamEntity finalExam1 = PassedExamFixture.passed(exam, "ellie");
    PassedExamEntity finalExam2 = PassedExamFixture.passed(exam, "lie");
    PassedExamEntity middleExam = PassedExamFixture.passed("중간고사", "elephant");
    Mockito.when(passedExamRepository.findAll())
        .thenReturn(List.of(finalExam1, finalExam2, middleExam));

    // when
    var expectResponse =
        List.of(finalExam1, finalExam2).stream()
            .map(
                passedExam ->
                    new ResponsePassedExamStudent(
                        passedExam.getStudentName(), passedExam.getAvgScore()))
            .toList();
    List<ResponsePassedExamStudent> passedExamStudentsList =
        studentScoreService.getPassedExamStudentsList(exam);

    // then
    Assertions.assertIterableEquals(expectResponse, passedExamStudentsList);
  }

  @Test
  @DisplayName("특정 시험에 대한 불합격자 명단 리스트 조회하기")
  void get_failed_exam_students_list_test() {
    // given
    String exam = "기말고사";
    FailedExamEntity finalExam1 = FailedExamFixture.failed(exam, "ellie");
    FailedExamEntity finalExam2 = FailedExamFixture.failed(exam, "lie");
    ;
    FailedExamEntity middleExam = FailedExamFixture.failed("중간고사", "elephant");
    Mockito.when(failedExamRepository.findAll())
        .thenReturn(List.of(finalExam1, finalExam2, middleExam));

    // when
    var expectResponse =
        List.of(finalExam1, finalExam2).stream()
            .map(
                passedExam ->
                    new ResponseFailedExamStudent(
                        passedExam.getStudentName(), passedExam.getAvgScore()))
            .toList();
    List<ResponseFailedExamStudent> failedExamStudentsList =
        studentScoreService.getFailedExamStudentsList(exam);

    // then
    Assertions.assertIterableEquals(expectResponse, failedExamStudentsList);
  }
}
