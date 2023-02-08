package com.sparta.finalproject.post.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.CreatePost;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserWithPost;
import com.sparta.finalproject.user.entity.User;
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

    @DisplayName("게시물 생성 & 단건 조회 테스트")
    @Transactional
    @Test
    void createPostTest() {
        // Given
        String title = "title";
        String content = "content";
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .build();
        PostDto.CreatePost createPost = CreatePost.builder()
            .title(title)
            .content(content)
            .build();
        ResponseUserWithPost userInfo = ResponseUserWithPost.of(user);

        // When
        Long postId = postService.createPost(createPost, user);
        PostDto.ResponsePost postById = postService.getPostById(postId);

        // Then
        assertThat(postById.getId()).isEqualTo(postId);
        assertThat(postById.getTitle()).isEqualTo(title);
        assertThat(postById.getContent()).isEqualTo(content);
        assertThat(postById.getUserInfo().getId()).isEqualTo(userInfo.getId());
        assertThat(postById.getUserInfo().getUserId()).isEqualTo(userInfo.getUserId());
    }
    
}