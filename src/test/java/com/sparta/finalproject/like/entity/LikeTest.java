package com.sparta.finalproject.like.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeTest {

    @DisplayName("1. 좋아요 빌더 테스트")
    @Test
    void likeBuilderTest() {
        // Given
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .build();

        Post post = Post.builder()
            .title("title")
            .content("content")
            .user(user)
            .build();

        // When
        Like like = Like.builder()
            .user(user)
            .post(post)
            .build();

        // Then
        assertThat(like.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(like.getPost().getTitle()).isEqualTo(post.getTitle());
    }

}