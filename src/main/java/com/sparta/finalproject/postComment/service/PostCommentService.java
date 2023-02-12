package com.sparta.finalproject.postComment.service;

import com.sparta.finalproject.postComment.dto.PostCommentDto.CreatePostComment;
import com.sparta.finalproject.user.entity.User;

public interface PostCommentService {

    Long createPostComment(Long postId, CreatePostComment createPostComment, User user);
}