package com.sparta.finalproject.post.dto;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.user.dto.ResponseUserWithPost;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ResponsePost {

    private Long id;
    private String title;
    private String content;
    private ResponseUserWithPost userInfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private ResponsePost(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userInfo = ResponseUserWithPost.of(post.getUser().getId(),
            post.getUser().getUserId());
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public static ResponsePost of(Post post) {
        
        return new ResponsePost(post);
    }

    public static List<ResponsePost> of(List<Post> posts) {

        return posts.stream().map(ResponsePost::of).collect(Collectors.toList());
    }
}