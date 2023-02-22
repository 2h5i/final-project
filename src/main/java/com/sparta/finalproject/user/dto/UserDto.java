package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
        @Size(min = 8, max = 15)
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "최소 8자 이상, 15자 이하이며 a-z, A-Z, 0-9 만 입력하세요.")
        private String password;

        @Builder
        public RequestUpdateUser(String password) {
            this.password = password;
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseUserListAdmin {

        private Long id;
        private String userId;
        private Long kakaoId;
        private String email;

        private ResponseUserListAdmin(User user) {
            this.id = user.getId();
            this.userId = user.getUserId();
            this.kakaoId = user.getKakaoId();
            this.email = user.getEmail();
        }

        public static ResponseUserListAdmin of(User user) {
            return new ResponseUserListAdmin(user);
        }

        public static List<ResponseUserListAdmin> of(List<User> users) {
            return users.stream().map(ResponseUserListAdmin::of).collect(Collectors.toList());
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SearchUserAdmin {

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeleteRequestDto {
        private String userId;
    }
}
