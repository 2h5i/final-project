package com.sparta.finalproject.recruitment.dto;

import com.sparta.finalproject.post.dto.PostDto.ResponsePost;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.time.LocalDateTime;
import lombok.Getter;

public class RecruitmentDto {

    @Getter
    public static class ResponseRecruitment{

        private Long id;
        private String title;
        private String subTitle;
        private String content;
        private String href;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public ResponseRecruitment (Recruitment recruitment) {
            this.id = recruitment.getId();
            this.title = recruitment.getTitle();
            this.subTitle = recruitment.getSubTitle();
            this.content = recruitment.getContent();
            this.href = recruitment.getHref();
            this.createdAt =  recruitment.getCreatedAt();
            this.modifiedAt = recruitment.getModifiedAt();
        }

        public static ResponseRecruitment of(Recruitment recruitment) {
            return new ResponseRecruitment(recruitment);
        }
    }
}
