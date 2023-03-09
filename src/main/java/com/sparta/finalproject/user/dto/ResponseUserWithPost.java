package com.sparta.finalproject.user.dto;

import lombok.Getter;

@Getter
public class ResponseUserWithPost {

    private Long id;
    private String userId;

    private ResponseUserWithPost(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public static ResponseUserWithPost of(Long id, String userId) {
        return new ResponseUserWithPost(id, userId);
    }
}