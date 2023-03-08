package com.sparta.finalproject.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchUserAdmin {

    private String userId;
    private Long kakaoId;
    private String email;

    @Builder
    public SearchUserAdmin(String userId, Long kakoId, String email) {
        this.userId = userId;
        this.kakaoId = kakoId;
        this.email = email;
    }
}