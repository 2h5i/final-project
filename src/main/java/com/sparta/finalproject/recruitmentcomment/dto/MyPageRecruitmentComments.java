package com.sparta.finalproject.recruitmentcomment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageRecruitmentComments {

    Long recruitmentId;
    String content;

    public MyPageRecruitmentComments(Long recruitmentId, String content) {
        this.recruitmentId = recruitmentId;
        this.content = content;
    }

}
