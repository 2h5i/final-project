package com.sparta.finalproject.recruitment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindNewRecruitment {

    private Long id;
    private String title;
    private String subTitle;

    public FindNewRecruitment(Long id, String title, String subTitle) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }
}
