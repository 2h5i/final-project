package com.sparta.finalproject.recruitment.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecruitmentServiceImplTest {

//    @Autowired
//    RecruitmentService recruitmentService;
//    @Autowired
//    RecruitmentRepository recruitmentRepository;
//
//    @DisplayName("1. 채용공고생성 및 단건조회")
//    @Test
//    @Transactional
//    @Rollback(false)
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
//        Recruitment createdRecruitment = recruitmentService.createRecruitment(
//            recruitment.getTitle(), recruitment.getSubTitle(),
//            recruitment.getContent(), recruitment.getHref());
//
//        ResponseRecruitment responseRecruitment = recruitmentService.selectRecruitmentById(
//            createdRecruitment.getId());
//
//        // then
//        Assertions.assertEquals(responseRecruitment.getTitle(), testTitle);
//        Assertions.assertEquals(responseRecruitment.getId(), createdRecruitment.getId());
//    }
}