package com.sparta.finalproject.recruitmentcomment.service;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import com.sparta.finalproject.recruitmentcomment.repository.RecruitmentCommentRepository;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RecruitmentCommentServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Autowired
    RecruitmentCommentRepository recruitmentCommentRepository;

    @Test
    @DisplayName("1. 댓글 구현 테스트")
    @Transactional
    void createRecruitmentComment() {
        //Given
        User user = User.builder()
            .userId("userId")
            .password("password")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        userRepository.save(user);

        Recruitment recruitment = Recruitment.builder()
            .title("title")
            .subTitle("subTitle")
            .content("content")
            .href("href")
            .build();

        recruitmentRepository.save(recruitment);
        RecruitmentComment recruitmentComment = RecruitmentComment.builder()
            .content("content")
            .user(user)
            .recruitment(recruitment)
            .build();

        //When
        recruitmentCommentRepository.save(recruitmentComment);

        //Then
        Assertions.assertEquals(recruitmentComment.getContent(), "content");
    }
}