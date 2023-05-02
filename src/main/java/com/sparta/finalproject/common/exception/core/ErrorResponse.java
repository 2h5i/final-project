package com.sparta.finalproject.common.exception.core;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private int code;
    private String message;

    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(int code, String message) {
        
        return new ErrorResponse(code, message);
    }
}