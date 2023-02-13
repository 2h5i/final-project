package com.sparta.finalproject.postcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostCommentDto {

    @Getter
    @NoArgsConstructor
    public static class CreatePostComment {

        @NotBlank
        private String content;

        @Builder
        public CreatePostComment(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdatePostComment {

        @NotBlank
        private String content;

        @Builder
        public UpdatePostComment(String content) {
            this.content = content;
        }
    }

}
