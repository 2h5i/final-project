package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import lombok.Getter;

@Getter
public class ResponseUserWithRecruitmentComment {

    private Long id;
    private String userId;

    private ResponseUserWithRecruitmentComment(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
    }

    public static ResponseUserWithRecruitmentComment of(User user) {
        return new ResponseUserWithRecruitmentComment(user);
    }
}
