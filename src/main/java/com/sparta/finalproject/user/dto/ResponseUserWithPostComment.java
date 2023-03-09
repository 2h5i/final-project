package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import lombok.Getter;

@Getter
public class ResponseUserWithPostComment {

    private Long id;
    private String userId;

    private ResponseUserWithPostComment(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
    }

    public static ResponseUserWithPostComment of(User user) {
        return new ResponseUserWithPostComment(user);
    }
}