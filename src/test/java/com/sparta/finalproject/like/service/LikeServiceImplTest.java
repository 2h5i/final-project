package com.sparta.finalproject.like.service;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LikeServiceImplTest {

    @Autowired
    LikeService likeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @DisplayName("1. 좋아요 누르기 & 갯수 조회 테스트")
    @Transactional
    @Test
    void createLikeTest() {
        // Given
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        User savedUser = userRepository.save(user);

        Post post = Post.builder()
            .title("title")
            .content("content")
            .user(savedUser)
            .build();

        Post savedPost = postRepository.save(post);

        // When
        likeService.createLike(savedPost.getId(), savedUser);

        // Then
        Long likeCount = likeService.selectLikeCount(savedPost.getId());

        Assertions.assertThat(likeCount).isEqualTo(1);
    }

}