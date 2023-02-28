package com.sparta.finalproject.postcomment.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostCommentServiceImplTest {
//
//    @Autowired
//    PostCommentService postCommentService;
//
//    @Autowired
//    PostCommentRepository postCommentRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PostRepository postRepository;
//
//    @DisplayName("1. 게시물 댓글 생성 테스트")
//    @Transactional
//    @Test
//    void createPostCommentTest() {
//        // Given
//        User user = User.builder()
//            .userId("userId")
//            .password("password")
//            .email("email@email.com")
//            .role(UserRole.USER)
//            .build();
//
//        User savedUser = userRepository.save(user);
//
//        Post post = Post.builder()
//            .title("title")
//            .content("content")
//            .user(savedUser)
//            .build();
//
//        Post savedPost = postRepository.save(post);
//
//        String postCommentContent = "post comment";
//
//        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
//            .content(postCommentContent)
//            .build();
//
//        // When
//        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
//            createPostComment, savedUser);
//
//        // Then
//        PostComment findPostComment = postCommentRepository.findById(postCommentId).get();
//
//        assertThat(findPostComment.getContent()).isEqualTo(postCommentContent);
//        assertThat(findPostComment.getUser().getUserId()).isEqualTo("userId");
//        assertThat(findPostComment.getPost().getTitle()).isEqualTo("title");
//    }
//
//    @DisplayName("2. 게시물 댓글 수정 테스트")
//    @Transactional
//    @Test
//    void updatePostCommentTest() {
//        // Given
//        User user = User.builder()
//            .userId("userId")
//            .password("password")
//            .email("email@email.com")
//            .role(UserRole.USER)
//            .build();
//
//        User savedUser = userRepository.save(user);
//
//        Post post = Post.builder()
//            .title("title")
//            .content("content")
//            .user(savedUser)
//            .build();
//
//        Post savedPost = postRepository.save(post);
//
//        String postCommentContent = "post comment";
//
//        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
//            .content(postCommentContent)
//            .build();
//
//        PostCommentDto.UpdatePostComment updatePostComment = PostCommentDto.UpdatePostComment.builder()
//            .content(postCommentContent)
//            .build();
//
//        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
//            createPostComment, savedUser);
//
//        // When
//        postCommentService.updatePostByPostCommentId(postCommentId, updatePostComment, user);
//
//        // Then
//        PostComment findPostComment = postCommentRepository.findById(postCommentId).get();
//
//        assertThat(findPostComment.getContent())
//            .isEqualTo(updatePostComment.getContent());
//        assertThat(findPostComment.getUser().getUserId()).isEqualTo("userId");
//        assertThat(findPostComment.getPost().getTitle()).isEqualTo("title");
//    }
//
//    @DisplayName("3. 게시물 댓글 삭제 테스트")
//    @Transactional
//    @Test
//    void deletePostCommentTest() {
//        // Given
//        User user = User.builder()
//            .userId("userId")
//            .password("password")
//            .email("email@email.com")
//            .role(UserRole.USER)
//            .build();
//
//        User savedUser = userRepository.save(user);
//
//        Post post = Post.builder()
//            .title("title")
//            .content("content")
//            .user(savedUser)
//            .build();
//
//        Post savedPost = postRepository.save(post);
//
//        String postCommentContent = "post comment";
//
//        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
//            .content(postCommentContent)
//            .build();
//
//        Long postCommentId = postCommentService.createPostComment(savedPost.getId(),
//            createPostComment, savedUser);
//
//        // When
//        postCommentService.deletePostByCommentId(postCommentId, user);
//
//        // Then
//        assertThatThrownBy(() -> postCommentRepository.findById(postCommentId).get())
//            .isInstanceOf(NoSuchElementException.class);
//    }
//
//    @DisplayName("4. 게시글 댓글 페이징 리스트 조회")
//    @Transactional
//    @Test
//    void selectPostCommentPagedListByPostIdTest() {
//        // Given
//        User user = User.builder()
//            .userId("userId")
//            .password("password")
//            .email("email@email.com")
//            .role(UserRole.USER)
//            .build();
//
//        User savedUser = userRepository.save(user);
//
//        Post post = Post.builder()
//            .title("title")
//            .content("content")
//            .user(savedUser)
//            .build();
//
//        Post savedPost = postRepository.save(post);
//
//        String postCommentContent = "post comment";
//
//        PostCommentDto.CreatePostComment createPostComment = PostCommentDto.CreatePostComment.builder()
//            .content(postCommentContent)
//            .build();
//
//        postCommentService.createPostComment(savedPost.getId(), createPostComment, savedUser);
//
//        // When
//        Page<ResponsePostCommentList> responsePostComments = postCommentService.selectPostCommentListByPostId(
//            savedPost.getId(), PageRequest.of(0, 10));
//
//        // Then
//        assertThat(responsePostComments.getTotalElements()).isEqualTo(1);
//    }

}