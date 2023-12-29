package com.project.eventlink.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommonException extends RuntimeException {
    private final ExceptionType exceptionType;
}
