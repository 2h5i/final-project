package com.sparta.finalproject.like.service;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
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

    @DisplayName("2. 좋아요 취소하기")
    @Transactional
    @Test
    void deleteLikeTest() {
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

        likeService.createLike(savedPost.getId(), savedUser);
        // TODO : 좋아요 조회

        // When
        // TODO : 좋아요 삭제

        // Then
        // TODO : 좋아요 삭제 됐는지 알아보기
    }

}