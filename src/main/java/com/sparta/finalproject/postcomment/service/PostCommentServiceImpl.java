package com.sparta.finalproject.postcomment.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.postcomment.dto.CreatePostComment;
import com.sparta.finalproject.postcomment.dto.PostCommentsDto;
import com.sparta.finalproject.postcomment.dto.ResponsePostCommentList;
import com.sparta.finalproject.postcomment.dto.UpdatePostComment;
import com.sparta.finalproject.postcomment.entity.PostComment;
import com.sparta.finalproject.postcomment.repository.PostCommentRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void updatePostByPostCommentId(Long postCommentId, UpdatePostComment updatePostComment,
        User user) {
        PostComment foundPostComment = postCommentRepository.findById(postCommentId).orElseThrow(
            () -> new BadRequestException("수정 할 댓글이 존재하지 않습니다.")
        );

        foundPostComment.validateUser(user);
        foundPostComment.editComment(updatePostComment.getContent());

        postCommentRepository.save(foundPostComment);
    }

    @Override
    @Transactional
    public void deletePostByCommentId(Long postCommentId, User user) {
        PostComment foundPostComment = postCommentRepository.findById(postCommentId).orElseThrow(
            () -> new BadRequestException("삭제 할 댓글이 존재하지 않습니다.")
        );

        foundPostComment.validateUser(user);

        postCommentRepository.delete(foundPostComment);
    }

    @Override
    @Transactional
    public Page<ResponsePostCommentList> selectPostCommentListByPostId(Long postId,
        Pageable pageable) {

        return postCommentRepository.selectPostCommentListByPostId(postId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostCommentsDto> selectMyCommentLists(Pageable pageable, Long userId) {
        return postCommentRepository.findByMyPageComment(pageable, userId);
    }
}
