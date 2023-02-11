package com.sparta.finalproject.postComment.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.postComment.dto.PostCommentDto.CreatePostComment;
import com.sparta.finalproject.postComment.entity.PostComment;
import com.sparta.finalproject.postComment.repository.PostCommentRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @Override
    public Long createPostComment(Long postId, CreatePostComment createPostComment, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("댓글을 달 게시물이 존재하지 않습니다.")
        );

        PostComment postComment = PostComment.builder()
            .content(createPostComment.getContent())
            .user(user)
            .post(post)
            .build();
        
        return postCommentRepository.save(postComment).getId();
    }
}
