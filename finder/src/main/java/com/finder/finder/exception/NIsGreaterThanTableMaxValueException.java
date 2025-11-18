package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NIsGreaterThanTableMaxValueException extends FinderParentException {

    public NIsGreaterThanTableMaxValueException(String message) {
        super(ErrorType.N_IS_TOO_BIG, "N is too big number : %s]".formatted(message));
    }
}

