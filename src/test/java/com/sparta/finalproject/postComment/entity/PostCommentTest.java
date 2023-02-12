package com.sparta.finalproject.postComment.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCommentTest {

    @DisplayName("1. 게시물 댓글 빌더 테스트")
    @Test
    void postCommentBuilderTest() {
        // Given
        String content = "post comment";
        User user = User.builder()
            .userId("userId")
            .password("passowrd")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        Post post = Post.builder()
            .title("title")
            .content("content")
            .build();

        // When
        PostComment postComment = PostComment.builder()
            .content(content)
            .user(user)
            .post(post)
            .build();

        // Then
        assertThat(postComment.getContent()).isEqualTo(content);
        assertThat(postComment.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(postComment.getPost().getTitle()).isEqualTo(post.getTitle());
    }

    @DisplayName("2. 게시물 댓글 수정 테스트")
    @Test
    void postCommentUpdateTest() {
        // Given
        String updateContent = "post comment update";
        User user = User.builder()
            .userId("userId")
            .password("passowrd")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        Post post = Post.builder()
            .title("title")
            .content("content")
            .build();

        PostComment postComment = PostComment.builder()
            .content("post comment")
            .user(user)
            .post(post)
            .build();

        // When
        postComment.editComment(updateContent);

        // Then
        assertThat(postComment.getContent()).isEqualTo(updateContent);
    }

}