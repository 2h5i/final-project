package com.sparta.finalproject.recruitmentcomment.dto;

import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserWithRecruitmentComment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RecruitmentCommentDto {

    @Getter
    @NoArgsConstructor
    public static class CreateRecruitmentComment {

        @NotBlank
        private String content;

        @Builder
        public CreateRecruitmentComment(String content) {
            this.content = content;
        }
    }

    @Getter
    public static class ResponseRecruitmentCommentList {

        private String content;
        private Long recruitmentCommentId;
        private ResponseUserWithRecruitmentComment user;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public ResponseRecruitmentCommentList(RecruitmentComment recruitmentComment) {
            this.content = recruitmentComment.getContent();
            this.recruitmentCommentId = recruitmentComment.getId();
            this.user = ResponseUserWithRecruitmentComment.of(recruitmentComment.getUser());
            this.createdAt = recruitmentComment.getCreatedAt();
            this.modifiedAt = recruitmentComment.getModifiedAt();
        }

        public static ResponseRecruitmentCommentList of(RecruitmentComment recruitmentComment) {
            return new ResponseRecruitmentCommentList(recruitmentComment);
        }

        public static List<ResponseRecruitmentCommentList> of(
            List<RecruitmentComment> recruitmentComments) {
            return recruitmentComments.stream().map(ResponseRecruitmentCommentList::of)
                .collect(Collectors.toList());
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SearchRecruitment {

        private String userId;
        private String content;

        @Builder
        public SearchRecruitment(String userId, String content) {
            this.userId = userId;
            this.content = content;
        }
    }
}
