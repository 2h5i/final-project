package com.sparta.finalproject.recruitmentcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class RecruitmentCommentDto {

    @Getter
    @Builder
    public class CreateRecruitmentComment {

        @NotBlank
        private String content;
    }
}
