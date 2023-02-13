package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.recruitment.dto.RecruitmentDto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class RecruitmentServiceImplTest {

    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    RecruitmentRepository recruitmentRepository;

    @DisplayName("1. 채용공고생성 및 단건조회")
    @Test
    @Transactional
    @Rollback(false)
    void selectRecruitmentById() {
        // Given
        Recruitment recruitment = Recruitment.builder()
            .title("title")
            .subTitle("subtitle")
            .content("content")
            .href("href")
            .build();

        String testTitle = "title";

        // When
        Recruitment createdRecruitment = recruitmentService.createRecruitment(
            recruitment.getTitle(), recruitment.getSubTitle(),
            recruitment.getContent(), recruitment.getHref());

        ResponseRecruitment responseRecruitment = recruitmentService.selectRecruitmentById(
            createdRecruitment.getId());

        // then
        Assertions.assertEquals(responseRecruitment.getTitle(), testTitle);
        Assertions.assertEquals(responseRecruitment.getId(), createdRecruitment.getId());
    }
}