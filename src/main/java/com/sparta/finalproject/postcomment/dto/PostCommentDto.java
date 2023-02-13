package com.sparta.finalproject.postcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class PostCommentDto {

    @Getter
    @Builder
    public static class CreatePostComment {

        @NotBlank
        private String content;
    }

    @Getter
    @Builder
    public static class UpdatePostComment {

        @NotBlank
        private String content;
    }

}
