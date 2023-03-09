package com.sparta.finalproject.recruitmentcomment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchRecruitment {

    private String userId;
    private String content;

    @Builder
    public SearchRecruitment(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
