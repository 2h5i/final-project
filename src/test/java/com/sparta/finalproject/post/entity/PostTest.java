package com.sparta.finalproject.post.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sparta.finalproject.common.exception.BadRequestException;
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

    @DisplayName("2. 게시글 수정 테스트")
    @Test
    void postUpdateTest() {
        // Given
        String title = "title";
        String content = "content";
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .build();
        Post post = Post.builder()
            .title(title)
            .content(content)
            .user(user)
            .build();
        String updateTitle = "title update";
        String updateContent = "title content";

        // When
        post.updatePost(updateTitle, updateContent);

        // Then
        assertThat(post.getTitle()).isEqualTo(updateTitle);
        assertThat(post.getContent()).isEqualTo(updateContent);
    }

    @DisplayName("3. 유효한 사용자 테스트")
    @Test
    void validUserTest() {
        User user1 = User.builder()
            .userId("user1")
            .password("password1")
            .email("email1@email.com")
            .build();

        User user2 = User.builder()
            .userId("user2")
            .password("password2")
            .email("email2@email.com")
            .build();

        Post post = Post.builder()
            .title("title")
            .content("content")
            .user(user1)
            .build();

        assertThatNoException().isThrownBy(() -> post.validateUser(user1));
        assertThatThrownBy(() -> post.validateUser(user2)).isInstanceOf(BadRequestException.class);
    }

}