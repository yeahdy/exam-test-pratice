package com.example.examservice.service;

import com.example.examservice.domain.FailedExamEntity;
import com.example.examservice.domain.PassedExamEntity;
import com.example.examservice.dto.StudentScoreDto;
import com.example.examservice.repository.FailedExamRepository;
import com.example.examservice.repository.PassedExamRepository;
import com.example.examservice.repository.StudentScoreRepository;
import com.example.examservice.utils.MyCalculator;
import com.example.examservice.vo.response.ResponseFailedExamStudent;
import com.example.examservice.vo.response.ResponsePassedExamStudent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentScoreService {

  private final StudentScoreRepository studentScoreRepository;
  private final PassedExamRepository passedExamRepository;
  private final FailedExamRepository failedExamRepository;

  public void saveScore(StudentScoreDto dto) {
    studentScoreRepository.save(dto.toEntity());

    MyCalculator calculator = new MyCalculator(0.0);
    Double avgScore =
        calculator
            .add(dto.getKorScore().doubleValue())
            .add(dto.getEnglishScore().doubleValue())
            .add(dto.getMathScore().doubleValue())
            .divid(3.0)
            .getResult();

    if (avgScore <= 60) {
      FailedExamEntity failedExamEntity =
          FailedExamEntity.builder()
              .exam(dto.getExam())
              .studentName(dto.getStudentName())
              .avgScore(avgScore)
              .build();
      failedExamRepository.save(failedExamEntity);
      return;
    }

    PassedExamEntity passedExamEntity =
        PassedExamEntity.builder()
            .exam(dto.getExam())
            .studentName(dto.getStudentName())
            .avgScore(avgScore)
            .build();
    passedExamRepository.save(passedExamEntity);
  }

  public List<ResponsePassedExamStudent> getPassedExamStudentsList(String exam) {
    List<PassedExamEntity> passedExams = passedExamRepository.findAll();
    return passedExams.stream()
        .filter((pass) -> pass.getExam().equals(exam))
        .map((pass) -> new ResponsePassedExamStudent(pass.getStudentName(), pass.getAvgScore()))
        .toList();
  }

  public List<ResponseFailedExamStudent> getFailedExamStudentsList(String exam) {
    List<FailedExamEntity> failedExams = failedExamRepository.findAll();
    return failedExams.stream()
        .filter((fail) -> fail.getExam().equals(exam))
        .map((fail) -> new ResponseFailedExamStudent(fail.getStudentName(), fail.getAvgScore()))
        .toList();
  }
}
