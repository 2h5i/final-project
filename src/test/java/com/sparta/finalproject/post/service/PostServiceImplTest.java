package com.sparta.finalproject.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.CreatePost;
import com.sparta.finalproject.post.dto.PostDto.ResponsePost;
import com.sparta.finalproject.post.dto.PostDto.UpdatePost;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PostServiceImplTest {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @DisplayName("1. 게시물 생성 & 단건 조회 테스트")
    @Test
    void createPostTest() {
        // Given
        String title = "title";
        String content = "content";
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        User savedUser = userRepository.save(user);

        PostDto.CreatePost createPost = CreatePost.builder()
            .title(title)
            .content(content)
            .build();

        // When
        Long postId = postService.createPost(createPost, savedUser);
        PostDto.ResponsePost postById = postService.getPostById(postId);

        // Then
        assertThat(postById.getId()).isEqualTo(postId);
        assertThat(postById.getTitle()).isEqualTo(title);
        assertThat(postById.getContent()).isEqualTo(content);
        assertThat(postById.getUserInfo().getId()).isEqualTo(savedUser.getId());
        assertThat(postById.getUserInfo().getUserId()).isEqualTo(savedUser.getUserId());
    }

    @DisplayName("2. 게시물 수정 테스트")
    @Test
    @Transactional
    void updatePostTest() {
        // Given
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        User savedUser = userRepository.save(user);

        PostDto.CreatePost createPost = CreatePost.builder()
            .title("title")
            .content("content")
            .build();

        String updateTitle = "update title";
        String updateContent = "update content";

        PostDto.UpdatePost updatePost = UpdatePost.builder()
            .title(updateTitle)
            .content(updateContent)
            .build();

        Long postId = postService.createPost(createPost, savedUser);

        // When
        postService.updatePost(postId, updatePost, savedUser);

        // Then
        ResponsePost updatedPost = postService.getPostById(postId);

        assertThat(updatedPost.getId()).isEqualTo(postId);
        assertThat(updatedPost.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedPost.getContent()).isEqualTo(updateContent);
    }

    @DisplayName("3. 게시물 삭제 테스트")
    @Test
    @Transactional
    void deletePostTest() {
        // Given
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        User savedUser = userRepository.save(user);

        PostDto.CreatePost createPost = CreatePost.builder()
            .title("title")
            .content("content")
            .build();

        Long postId = postService.createPost(createPost, savedUser);

        // When
        postService.deletePost(postId, savedUser);

        // Then
        assertThatThrownBy(() -> postService.getPostById(postId)).isInstanceOf(
                BadRequestException.class)
            .hasMessageContaining("해당하는 게시물이 없습니다.");
    }

}