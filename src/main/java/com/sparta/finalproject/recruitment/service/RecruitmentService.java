package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.recruitment.dto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {

    Recruitment createRecruitment(String title, String subTitle, String content, String href);

    ResponseRecruitment selectRecruitmentById(Long recruitmentId);

    Page<ResponseRecruitment> selectRecruitments(Pageable pageable,
        SearchRecruitment searchRecruitment);

    List<Map<String, Object>> mainRecruitments();

    void deleteRecruitmentAdmin(Long recruitmentId);

    boolean checkRecruitment(String href);
}
