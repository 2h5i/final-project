package com.sparta.finalproject.common.exception.core;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    BAD_REQUEST_EXCEPTION(400);

    private int code;

    ErrorStatus(int code) {
        this.code = code;
    }
}
