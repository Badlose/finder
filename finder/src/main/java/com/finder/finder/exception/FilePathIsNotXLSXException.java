package com.finder.finder.exception;

import com.finder.finder.exception.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilePathIsNotXLSXException extends FinderParentException {

    public FilePathIsNotXLSXException(String message) {
        super(ErrorType.FILE_IS_NOT_XLSX, "File is not .xlsx : %s]".formatted(message));
    }
}

