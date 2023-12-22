package com.project.eventlink.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.project.eventlink")
public class CommonControllerAdvice {

    @ExceptionHandler(value = {CommonException.class})
    public ResponseEntity<ErrorResult> commonExceptionHandler(CommonException e) {
        ExceptionType exceptionType = e.getExceptionType();
        log.error("Exception Throw : {}", exceptionType);
        return ErrorResult.toResponseEntity(exceptionType);
    }
}
