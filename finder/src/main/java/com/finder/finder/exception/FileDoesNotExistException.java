package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileDoesNotExistException extends FinderParentException {

    public FileDoesNotExistException(String message) {
        super(ErrorType.FILE_DOES_NOT_EXIST, "File does not exist: %s]".formatted(message));
    }
}
