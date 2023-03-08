package com.sparta.finalproject.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchPost {

    private String title;
    private String content;

    @Builder
    public SearchPost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
