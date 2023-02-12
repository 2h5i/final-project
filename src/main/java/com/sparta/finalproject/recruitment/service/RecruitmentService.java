package com.sparta.finalproject.recruitment.service;


import com.sparta.finalproject.recruitment.dto.RecruitmentDto;

public interface RecruitmentService {

    void createRecruitment(String title, String subTitle, String content, String href);

    RecruitmentDto.ResponseRecruitment selectRecruitmentById(Long recruitmentId);

}
