package com.sparta.finalproject.recruitmentcomment.dto;

import javax.validation.constraints.NotBlank;

public class RecruitmentCommentDto {

    public class CreateComment {

        @NotBlank
        private String content;
    }

}
