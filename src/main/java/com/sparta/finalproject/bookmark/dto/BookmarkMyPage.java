package com.sparta.finalproject.bookmark.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkMyPage {

    Long recruitmentId;
    String title;

    
    public BookmarkMyPage(Long recruitmentId, String title) {
        this.recruitmentId = recruitmentId;
        this.title = title;
    }
}
