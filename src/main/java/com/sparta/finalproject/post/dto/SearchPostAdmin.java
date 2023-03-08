package com.sparta.finalproject.post.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchPostAdmin {

    private String title;
    private String content;
    private String userId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdStarted;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdEnded;

    @Builder
    public SearchPostAdmin(String title, String content, String userId,
        LocalDateTime createdStarted, LocalDateTime createdEnded) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdStarted = createdStarted;
        this.createdEnded = createdEnded;
    }
}