package com.example.firstservice.controller;

import com.example.firstservice.vo.request.RequestSaveScore;
import com.example.firstservice.vo.response.ResponseFailedExamStudent;
import com.example.firstservice.vo.response.ResponsePassedExamStudent;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @PutMapping("/exam/{exam}/score")
    public Object saveScore(@PathVariable("exam") String exam, @RequestBody RequestSaveScore request) {
        return request;
    }

    @GetMapping("/exam/{exam}/pass")
    public List<ResponsePassedExamStudent> passTest(@PathVariable("exam") String exam) {
        return List.of(
                new ResponsePassedExamStudent("ellie", 80.5),
                new ResponsePassedExamStudent("yeah", 81.5),
                new ResponsePassedExamStudent("dy", 85.5)
        );
    }

    @GetMapping("/exam/{exam}/fail")
    public List<ResponseFailedExamStudent> failTest(@PathVariable("exam") String exam) {
        return List.of(
                new ResponseFailedExamStudent("happy", 45.6),
                new ResponseFailedExamStudent("mike", 55.0)
        );
    }

}
