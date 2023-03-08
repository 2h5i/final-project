package com.sparta.finalproject.postcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostComment {

    @NotBlank(message = "댓글 내용을 입력해주세요")
    private String content;

    @Builder
    public CreatePostComment(String content) {
        this.content = content;
    }
}