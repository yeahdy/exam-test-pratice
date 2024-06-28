package com.example.firstservice.controller;

import com.example.firstservice.service.StudentScoreService;
import com.example.firstservice.vo.request.RequestSaveScore;
import com.example.firstservice.vo.response.ResponseFailedExamStudent;
import com.example.firstservice.vo.response.ResponsePassedExamStudent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreController {

    private final StudentScoreService studentScoreService;

    @PutMapping("/exam/{exam}/score")
    public void saveScore(@PathVariable("exam") String exam, @RequestBody RequestSaveScore request) {
        studentScoreService.saveScore(request.toDto(exam));
    }

    @GetMapping("/exam/{exam}/pass")
    public List<ResponsePassedExamStudent> passTest(@PathVariable("exam") String exam) {
        return studentScoreService.getPassedExamStudentsList(exam);
    }

    @GetMapping("/exam/{exam}/fail")
    public List<ResponseFailedExamStudent> failTest(@PathVariable("exam") String exam) {
        return studentScoreService.getFailedExamStudentsList(exam);
    }

}
