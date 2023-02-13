package com.sparta.finalproject.postcomment.service;

import com.sparta.finalproject.postcomment.dto.PostCommentDto;
import com.sparta.finalproject.postcomment.dto.PostCommentDto.CreatePostComment;
import com.sparta.finalproject.postcomment.dto.PostCommentDto.UpdatePostComment;
import com.sparta.finalproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCommentService {

    Long createPostComment(Long postId, CreatePostComment createPostComment, User user);

    void updatePostByPostCommentId(Long postCommentId, UpdatePostComment updatePostComment,
        User user);

    void deletePostByCommentId(Long postCommentId, User user);

    Page<PostCommentDto.ResponsePostCommentList> selectPostCommentListByPostId(Long postId,
        Pageable pageable);
}
