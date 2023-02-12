package com.sparta.finalproject.recruitment.service;


import com.sparta.finalproject.recruitment.dto.RecruitmentDto;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {

    void createRecruitment(String title, String subTitle, String content, String href);

    RecruitmentDto.ResponseRecruitment selectRecruitmentById(Long recruitmentId);

    Page<RecruitmentDto.ResponseRecruitment> selectRecruitments(Pageable pageable,
        SearchRecruitment searchRecruitment);

}
