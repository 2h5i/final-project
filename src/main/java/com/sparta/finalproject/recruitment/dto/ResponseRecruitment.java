package com.sparta.finalproject.recruitment.dto;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ResponseRecruitment {

    private Long id;
    private String title;
    private String subTitle;
    private String content;
    private String href;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResponseRecruitment(Recruitment recruitment) {
        this.id = recruitment.getId();
        this.title = recruitment.getTitle();
        this.subTitle = recruitment.getSubTitle();
        this.content = recruitment.getContent();
        this.href = recruitment.getHref();
        this.createdAt = recruitment.getCreatedAt();
        this.modifiedAt = recruitment.getModifiedAt();
    }

    public static ResponseRecruitment of(Recruitment recruitment) {
        return new ResponseRecruitment(recruitment);
    }

    public static List<ResponseRecruitment> of(List<Recruitment> recruitments) {
        return recruitments.stream().map(ResponseRecruitment::of).collect(Collectors.toList());
    }
}
