package com.sparta.finalproject.like.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.like.entity.Like;
import com.sparta.finalproject.like.repository.LikeRepository;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    public void createLike(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("게시물이 존재하지 않습니다.")
        );

        validateLike(postId, user);

        Like like = Like.builder()
            .user(user)
            .post(post)
            .build();

        likeRepository.save(like);
    }

    private void validateLike(Long postId, User user) {
        if (likeRepository.existsByPostIdAndUserId(postId, user.getId())) {
            throw new BadRequestException("이미 좋아요를 눌렀습니다.");
        }
    }
}