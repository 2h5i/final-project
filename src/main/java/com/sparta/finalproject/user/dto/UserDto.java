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
    public static class UpdateUser {

        @NotBlank
        private String userId;

        @NotBlank
        private String password;

        @NotBlank
        private String email;

        @Builder
        public UpdateUser(String userId, String password, String email) {
            this.userId = userId;
            this.password = password;
            this.email = email;
        }
    }
}
