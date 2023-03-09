package com.sparta.finalproject.recruitmentcomment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRecruitmentComment {

    @NotBlank(message = "댓글 내용을 입력해주세요")
    private String content;

    @Builder
    public CreateRecruitmentComment(String content) {
        this.content = content;
    }
}