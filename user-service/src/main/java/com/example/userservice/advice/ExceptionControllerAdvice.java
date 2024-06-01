package com.example.userservice.advice;

import com.example.userservice.common.response.ResponseMessage;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.userservice.controller")
public class ExceptionControllerAdvice {


    @ExceptionHandler
    public ResponseMessage<?> methodArgumentNotValidExHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = bindingResult.getFieldErrors().stream().collect(
                Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseMessage.setMessage(HttpStatus.BAD_REQUEST,String.format("Bad Parameter :: %s",getErrorMessage(map)),e.getClass());
    }

    private String getErrorMessage(Map<String, String> map){
        StringBuilder messageBuild = new StringBuilder();
        for (String s : map.values()) {
            messageBuild.append(s).append(",");
        }

        String message = messageBuild.toString();
        return message.endsWith(",") ? message.substring(0, message.length() - 1) : message;
    }

    @ExceptionHandler
    public ResponseMessage<?> validateExHandler(NullPointerException e) {
        return ResponseMessage.setMessage(HttpStatus.BAD_REQUEST,String.format("Bad Parameter :: %s",e.getMessage()),e.getClass());
    }

}
