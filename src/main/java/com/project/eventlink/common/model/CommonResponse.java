package com.project.eventlink.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
public record CommonResponse<T>(int statusCode, HttpStatus status, T data) {

    public static <T> CommonResponse toResponse(HttpStatus status, T data) {
        return CommonResponse.builder()
                .statusCode(status.value())
                .status(status)
                .data(data)
                .build();
    }
}
