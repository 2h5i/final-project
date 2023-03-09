package com.sparta.finalproject.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUserAdmin {

    private Long id;
    private String userId;
    private Long kakaoId;
    private String email;
    private String profileImage;

    @Builder
    public ResponseUserAdmin(Long id, String userId, Long kakaoId, String email,
        String profileImage) {
        this.id = id;
        this.userId = userId;
        this.kakaoId = kakaoId;
        this.email = email;
        this.profileImage = profileImage;
    }
}