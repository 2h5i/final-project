package com.sparta.finalproject.postcomment.dto;

import com.sparta.finalproject.postcomment.entity.PostComment;
import com.sparta.finalproject.user.dto.ResponseUserWithPostComment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ResponsePostCommentList {

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
        return postComments.stream().map(ResponsePostCommentList::of).collect(Collectors.toList());
    }
}
