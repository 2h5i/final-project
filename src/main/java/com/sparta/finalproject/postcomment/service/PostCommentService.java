package com.sparta.finalproject.postcomment.service;

import com.sparta.finalproject.postcomment.dto.PostCommentDto.CreatePostComment;
import com.sparta.finalproject.postcomment.dto.PostCommentDto.UpdatePostComment;
import com.sparta.finalproject.user.entity.User;

public interface PostCommentService {

    Long createPostComment(Long postId, CreatePostComment createPostComment, User user);

    void updatePostByPostCommentId(Long postCommentId, UpdatePostComment updatePostComment,
        User user);
}
