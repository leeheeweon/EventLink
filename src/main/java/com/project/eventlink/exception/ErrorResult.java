package com.project.eventlink.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResult {

    private final int statusCode;
    private final String status;
    private final String errorCode;
    private final String message;

    public static ResponseEntity<ErrorResult> toResponseEntity(ExceptionType exceptionType) {
        return ResponseEntity
                .status(exceptionType.getStatus())
                .body(ErrorResult.builder()
                        .statusCode(exceptionType.getStatus().value())
                        .status(exceptionType.name())
                        .errorCode(exceptionType.getErrorCode())
                        .message(exceptionType.getMessage())
                        .build());
    }
}
