package com.sparta.finalproject.like.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LikeServiceImplTest {

//    @Autowired
//    LikeService likeService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PostRepository postRepository;
//
//    @DisplayName("1. 좋아요 누르기 & 갯수 조회 테스트")
//    @Transactional
//    @Test
//    void createLikeTest() {
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
//        // When
//        likeService.createLike(savedPost.getId(), savedUser);
//
//        // Then
//        Long likeCount = likeService.selectLikeCount(savedPost.getId());
//
//        assertThat(likeCount).isEqualTo(1);
//    }
//
//    @DisplayName("2. 좋아요 취소 테스트")
//    @Transactional
//    @Test
//    void deleteLikeTest() {
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
//        likeService.createLike(savedPost.getId(), savedUser);
//
//        // When
//        likeService.deleteLike(savedPost.getId(), savedUser);
//
//        // Then
//        Long likeCount = likeService.selectLikeCount(savedPost.getId());
//
//        assertThat(likeCount).isEqualTo(0);
//    }

}