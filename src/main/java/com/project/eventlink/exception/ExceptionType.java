package com.project.eventlink.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "I1000", "아이템이 없습니다."),
    ITEM_LIST_EMPTY(HttpStatus.NOT_FOUND, "I1001", "아이템 리스트가 비어있습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
