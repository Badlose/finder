package com.finder.finder.controller;

import com.finder.finder.exception.FinderParentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleApiException(FinderParentException e) {
        ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        log.error("[error]    Exception has occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(responseStatus.value()).body(e.getMessage());
    }

}
