package com.sparta.finalproject.common.exception;

import com.sparta.finalproject.common.exception.core.ErrorResponse;
import com.sparta.finalproject.common.exception.core.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handelBadRequestException(final BadRequestException exception) {
        return ErrorResponse.of(ErrorStatus.BAD_REQUEST_EXCEPTION.getCode(),
            exception.getMessage());
    }
}
