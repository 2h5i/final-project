package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import lombok.Getter;

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

}
