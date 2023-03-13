package com.sparta.finalproject.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPagePost {

    Long postId;
    String Title;

    public MyPagePost(Long postId, String title) {
        this.postId = postId;
        Title = title;
    }
}
