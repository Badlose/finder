package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FileHasNoNumbersException extends FinderParentException {

    public FileHasNoNumbersException(String message) {
        super(ErrorType.FILE_HAS_NO_NUMBERS_EXCEPTION, "File has no numbers: [%s]".formatted(message));
    }
}
