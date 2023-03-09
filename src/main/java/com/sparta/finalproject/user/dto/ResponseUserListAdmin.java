package com.sparta.finalproject.user.dto;

import com.sparta.finalproject.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUserListAdmin {

    private Long id;
    private String userId;
    private Long kakaoId;
    private String email;
    private LocalDateTime createdAt;

    private ResponseUserListAdmin(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.kakaoId = user.getKakaoId();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

    public static ResponseUserListAdmin of(User user) {
        return new ResponseUserListAdmin(user);
    }

    public static List<ResponseUserListAdmin> of(List<User> users) {
        return users.stream().map(ResponseUserListAdmin::of).collect(Collectors.toList());
    }
}