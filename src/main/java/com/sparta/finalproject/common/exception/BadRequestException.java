package com.sparta.finalproject.common.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("잘못된 요청입니다.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}


