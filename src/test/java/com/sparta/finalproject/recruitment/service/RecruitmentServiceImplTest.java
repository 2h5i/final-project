package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecruitmentServiceImplTest {

    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    RecruitmentRepository recruitmentRepository;

//    @DisplayName("1. 채용공고생성 및 단건조회")
//    @Test
//    @Transactional
//    void selectRecruitmentById() {
//        // Given
//        Recruitment recruitment = Recruitment.builder()
//            .title("title")
//            .subTitle("subtitle")
//            .content("content")
//            .href("href")
//            .build();
//
//        String testTitle = "title";
//
//        // When
//        recruitmentService.createRecruitment(recruitment.getTitle(), recruitment.getSubTitle(),
//            recruitment.getContent(), recruitment.getHref());
//        recruitmentRepository.flush();
//
//        ResponseRecruitment responseRecruitment = recruitmentService.selectRecruitmentById(1L);
//
//        // then
//        Assertions.assertEquals(responseRecruitment.getTitle(), testTitle);
//        Assertions.assertEquals(responseRecruitment.getId(), 1);
//    }
}