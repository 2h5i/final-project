package com.sparta.finalproject.post.dto;

import com.sparta.finalproject.user.dto.ResponseUserWithPost;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponsePostList {

    private Long id;
    private String title;
    private String content;
    private ResponseUserWithPost userInfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCnt;

    public ResponsePostList(Long id, String title, String content, Long userId,
        String username, LocalDateTime createdAt, LocalDateTime modifiedAt, Long likeCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userInfo = ResponseUserWithPost.of(userId, username);
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeCnt = likeCnt;
    }
}