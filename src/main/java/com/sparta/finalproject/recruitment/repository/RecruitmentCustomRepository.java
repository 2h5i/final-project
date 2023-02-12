package com.sparta.finalproject.recruitment.repository;

import com.sparta.finalproject.recruitment.dto.RecruitmentDto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCustomRepository {

    Page<ResponseRecruitment> selectRecruitmentsBySearchCondition (Pageable pageable,
        SearchRecruitment searchRecruitment);

}
