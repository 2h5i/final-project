package com.sparta.finalproject.post.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.finalproject.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    @DisplayName("1. 게시글 빌더 테스트")
    @Test
    void postBuilderTest() {
        // Given
        String title = "title";
        String content = "content";
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .build();

        // When
        Post post = Post.builder()
            .title(title)
            .content(content)
            .user(user)
            .build();

        // Then
        assertThat(post.getId()).isNull();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getUser()).isEqualTo(user);
    }

}