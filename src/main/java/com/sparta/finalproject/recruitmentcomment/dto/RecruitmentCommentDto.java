package com.sparta.finalproject.recruitmentcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class RecruitmentCommentDto {

    @Getter
    public class CreateRecruitmentComment {

        @NotBlank
        private String content;
    }

}
