package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidNException extends FinderParentException {

    public InvalidNException(String message) {
        super(ErrorType.INVALID_N, "N-number is invalid: [%s]".formatted(message));
    }
}

