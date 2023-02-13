package com.sparta.finalproject.recruitment.service;


import com.sparta.finalproject.recruitment.dto.RecruitmentDto;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {

    Recruitment createRecruitment(String title, String subTitle, String content, String href);

    RecruitmentDto.ResponseRecruitment selectRecruitmentById(Long recruitmentId);

    Page<RecruitmentDto.ResponseRecruitment> selectRecruitments(Pageable pageable,
        SearchRecruitment searchRecruitment);

    Page<Recruitment> mainRecruitments();
}
