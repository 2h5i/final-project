package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    public static class ResponseUserWithPost {

        private Long id;
        private String userId;

        private ResponseUserWithPost(User user) {
            this.id = user.getId();
            this.userId = user.getUserId();
        }

        public static ResponseUserWithPost of(User user) {
            return new ResponseUserWithPost(user);
        }
    }

    @Getter
    public static class ResponseUserWithPostComment {

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

    @Getter
    public static class ResponseUserWithRecruitmentComment {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RequestUpdateUser {

        @NotBlank
        private String userId;
        @NotBlank
        private String password;
        @NotBlank
        private String email;

        @Builder
        public RequestUpdateUser(String userId, String password, String email) {
            this.userId = userId;
            this.password = password;
            this.email = email;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseUser {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseUserAdmin {

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
}
