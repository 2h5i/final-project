package com.sparta.finalproject.postcomment.dto;

import com.sparta.finalproject.postcomment.entity.PostComment;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserWithPostComment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostCommentDto {

    @Getter
    @NoArgsConstructor
    public static class CreatePostComment {

        @NotBlank(message = "댓글 내용을 입력해주세요")
        private String content;

        @Builder
        public CreatePostComment(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdatePostComment {

        @NotBlank(message = "댓글 내용을 입력해주세요")
        private String content;

        @Builder
        public UpdatePostComment(String content) {
            this.content = content;
        }
    }

    @Getter
    public static class ResponsePostCommentList {

        private String content;
        private Long postCommentId;
        private ResponseUserWithPostComment user;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private ResponsePostCommentList(PostComment postComment) {
            this.content = postComment.getContent();
            this.postCommentId = postComment.getId();
            this.user = ResponseUserWithPostComment.of(postComment.getUser());
            this.createdAt = postComment.getCreatedAt();
            this.modifiedAt = postComment.getModifiedAt();
        }

        public static ResponsePostCommentList of(PostComment postComment) {
            return new ResponsePostCommentList(postComment);
        }

        public static List<ResponsePostCommentList> of(List<PostComment> postComments) {
            return postComments.stream().map(ResponsePostCommentList::of)
                .collect(Collectors.toList());
        }
    }

}
