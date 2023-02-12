package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecruitmentServiceImplTest {

    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    RecruitmentRepository recruitmentRepository;

    @DisplayName("1. 채용공고생성 및 단건조회")
    @Test
    void selectRecruitmentById () {
        // Given
        Recruitment recruitment = Recruitment.builder()
            .title("title")
            .subTitle("subtitle")
            .content("content")
            .href("href")
            .build();

        String testTitle = "title";

        // When
        recruitmentRepository.save(recruitment);

        // then
        Assertions.assertEquals(recruitment.getTitle(),testTitle);
        Assertions.assertEquals(recruitment.getId(),1);
    }
}