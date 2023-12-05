package com.project.eventlink.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    private final int statusCode;
    private final HttpStatus status;
    private final T data;

    public static <T> CommonResponse toResponse(HttpStatus status, T data) {
        return CommonResponse.builder()
                .statusCode(status.value())
                .status(status)
                .data(data)
                .build();
    }
}
