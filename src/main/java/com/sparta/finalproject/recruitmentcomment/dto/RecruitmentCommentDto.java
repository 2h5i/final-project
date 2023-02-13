package com.sparta.finalproject.recruitmentcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RecruitmentCommentDto {

    @Getter
    @NoArgsConstructor
    public static class CreateRecruitmentComment {

        @NotBlank
        private String content;

        @Builder
        public CreateRecruitmentComment(String content) {
            this.content = content;
        }
    }
}
