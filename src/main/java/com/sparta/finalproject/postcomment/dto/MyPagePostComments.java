package com.sparta.finalproject.postcomment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPagePostComments {

    Long postId;
    String content;

    public MyPagePostComments(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }
}
