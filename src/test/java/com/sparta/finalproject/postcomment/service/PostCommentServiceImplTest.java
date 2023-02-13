package com.sparta.finalproject.postcomment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.postcomment.dto.PostCommentDto;
import com.sparta.finalproject.postcomment.entity.PostComment;
import com.sparta.finalproject.postcomment.repository.PostCommentRepository;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PostCommentServiceImplTest {

    @Autowired
    PostCommentService postCommentService;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @DisplayName("1. 게시물 댓글 생성 테스트")
    @Transactional
    @Test
    void createPostCommentTest() {
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

        String postCommentContent = "post comment";

        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
            .content(postCommentContent)
            .build();

        // When
        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
            createPostComment, savedUser);

        // Then
        PostComment findPostComment = postCommentRepository.findById(postCommentId).get();

        assertThat(findPostComment.getContent()).isEqualTo(postCommentContent);
        assertThat(findPostComment.getUser().getUserId()).isEqualTo("userId");
        assertThat(findPostComment.getPost().getTitle()).isEqualTo("title");
    }

    @DisplayName("2. 게시물 댓글 수정 테스트")
    @Transactional
    @Test
    void updatePostCommentTest() {
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

        String postCommentContent = "post comment";

        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
            .content(postCommentContent)
            .build();

        PostCommentDto.UpdatePostComment updatePostComment = PostCommentDto.UpdatePostComment.builder()
            .content(postCommentContent)
            .build();

        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
            createPostComment, savedUser);

        // When
        postCommentService.updatePostByPostCommentId(postCommentId, updatePostComment, user);

        // Then
        PostComment findPostComment = postCommentRepository.findById(postCommentId).get();

        assertThat(findPostComment.getContent())
            .isEqualTo(updatePostComment.getContent());
        assertThat(findPostComment.getUser().getUserId()).isEqualTo("userId");
        assertThat(findPostComment.getPost().getTitle()).isEqualTo("title");
    }

    @DisplayName("3. 게시물 댓글 삭제 테스트")
    @Transactional
    @Test
    void deletePostCommentTest() {
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

        String postCommentContent = "post comment";

        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
            .content(postCommentContent)
            .build();

        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
            createPostComment, savedUser);

        // When
        postCommentService.deletePostByCommentId(postCommentId, user);

        // Then
        assertThatThrownBy(() -> postCommentRepository.findById(postCommentId).get())
            .isInstanceOf(NoSuchElementException.class);
    }

}