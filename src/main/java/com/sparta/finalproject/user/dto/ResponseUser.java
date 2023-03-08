package com.sparta.finalproject.user.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUser {

    @NotBlank
    private String userId;
    @NotBlank
    private String profileImage;
    @NotBlank
    private String email;

    @Builder
    public ResponseUser(String userId, String profileImage, String email) {
        this.userId = userId;
        this.profileImage = profileImage;
        this.email = email;
    }
}