package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import lombok.Getter;


@Getter
public class FinderParentException extends RuntimeException {

    private final ErrorType errorType;

    public FinderParentException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }
}
