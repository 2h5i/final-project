package com.sparta.finalproject.postComment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class PostCommentDto {

    @Getter
    public static class CreatePostComment {

        @NotBlank
        private String content;
    }

}
