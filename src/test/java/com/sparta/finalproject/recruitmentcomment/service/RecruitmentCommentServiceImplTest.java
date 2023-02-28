package com.sparta.finalproject.recruitmentcomment.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class RecruitmentCommentServiceImplTest {

//    @Autowired
//    RecruitmentCommentService recruitmentCommentService;
//
//    @Autowired
//    RecruitmentCommentRepository recruitmentCommentRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RecruitmentRepository recruitmentRepository;
//
//    @Test
//    @DisplayName("1. 채용공고 댓글 생성 테스트")
//    void createRecruitmentComment() {
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
//        Recruitment recruitment = Recruitment.builder()
//            .title("title")
//            .subTitle("subTitle")
//            .content("content")
//            .href("href")
//            .build();
//
//        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);
//
//        String recruitmentComment = "comment";
//
//        RecruitmentCommentDto.CreateRecruitmentComment createRecruitmentComment =
//            RecruitmentCommentDto.CreateRecruitmentComment.builder()
//                .content(recruitmentComment)
//                .build();
//
//        // When
//        Long recruitmentId = recruitmentCommentService.createRecruitmentComment(savedRecruitment
//            .getId(), createRecruitmentComment, savedUser);
//
//        // Then
//        RecruitmentComment findRecruitmentComment = recruitmentCommentRepository
//            .findById(recruitmentId).get();
//
//        assertThat(findRecruitmentComment.getContent()).isEqualTo(recruitmentComment);
//        assertThat(findRecruitmentComment.getUser().getUserId()).isEqualTo("userId");
//        assertThat(findRecruitmentComment.getRecruitment().getHref()).isEqualTo("href");
//    }
//
//    @Test
//    @DisplayName("2. 채용공고 댓글 수정 테스트")
//    void updateRecruitmentComment() {
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
//        Recruitment recruitment = Recruitment.builder()
//            .title("title")
//            .subTitle("subTitle")
//            .content("content")
//            .href("href")
//            .build();
//
//        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);
//
//        String recruitmentComment = "comment";
//
//        RecruitmentCommentDto.CreateRecruitmentComment createRecruitmentComment =
//            RecruitmentCommentDto.CreateRecruitmentComment.builder()
//                .content(recruitmentComment)
//                .build();
//
//        String updateRecruitmentCommentString = "update comment";
//
//        RecruitmentCommentDto.CreateRecruitmentComment updateRecruitmentComment =
//            RecruitmentCommentDto.CreateRecruitmentComment.builder()
//                .content(updateRecruitmentCommentString)
//                .build();
//
//        Long recruitmentId = recruitmentCommentService.createRecruitmentComment(savedRecruitment
//            .getId(), createRecruitmentComment, savedUser);
//
//        // When
//        recruitmentCommentService.updateRecruitmentComment(recruitmentId, updateRecruitmentComment,
//            user);
//
//        // Then
//        RecruitmentComment findRecruitmentComment = recruitmentCommentRepository
//            .findById(recruitmentId).get();
//
//        assertThat(findRecruitmentComment.getContent()).isEqualTo("update comment");
//    }
//
//    @Test
//    @DisplayName("3. 채용공고 댓글 삭제 테스트")
//    void deleteRecruitmentComment() {
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
//        Recruitment recruitment = Recruitment.builder()
//            .title("title")
//            .subTitle("subTitle")
//            .content("content")
//            .href("href")
//            .build();
//
//        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);
//
//        String recruitmentComment = "comment";
//
//        RecruitmentCommentDto.CreateRecruitmentComment createRecruitmentComment =
//            RecruitmentCommentDto.CreateRecruitmentComment.builder()
//                .content(recruitmentComment)
//                .build();
//
//        Long recruitmentId = recruitmentCommentService.createRecruitmentComment(savedRecruitment
//            .getId(), createRecruitmentComment, savedUser);
//
//        RecruitmentComment findRecruitmentComment = recruitmentCommentRepository
//            .findById(recruitmentId).get();
//
//        // When
//        recruitmentCommentService.deleteRecruitmentComment(recruitmentId, user);
//
//        // Then
//        assertThatThrownBy(() -> recruitmentCommentRepository.findById(recruitmentId).get())
//            .isInstanceOf(NoSuchElementException.class);
//    }
}
