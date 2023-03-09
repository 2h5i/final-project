package com.sparta.finalproject.recruitment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchRecruitment {

    private String title;
    private String content;
}