package com.sparta.finalproject.like.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.like.entity.Like;
import com.sparta.finalproject.like.repository.LikeRepository;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void deleteLike(Long postId, User user) {
        Like like = likeRepository.findByPostIdAndUserId(postId, user.getId()).orElseThrow(
            () -> new BadRequestException("해당 게시글에 좋아요를 누르지 않았습니다.")
        );

        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public Long selectLikeCount(Long postId) {

        return likeRepository.countByPostId(postId);
    }

    @Override
    public boolean selectLikeCheck(Long postId, User user) {
        
        return likeRepository.existsByPostIdAndUser(postId, user);
    }

    private void validateLike(Long postId, User user) {
        if (likeRepository.existsByPostIdAndUserId(postId, user.getId())) {
            throw new BadRequestException("이미 좋아요를 눌렀습니다.");
        }
    }
}
