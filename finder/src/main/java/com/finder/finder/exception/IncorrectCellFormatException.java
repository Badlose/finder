package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IncorrectCellFormatException extends FinderParentException {

    public IncorrectCellFormatException(String message) {
        super(ErrorType.INCORRECT_CELL_FORMAT, "Incorrect cell format: %s]".formatted(message));
    }
}