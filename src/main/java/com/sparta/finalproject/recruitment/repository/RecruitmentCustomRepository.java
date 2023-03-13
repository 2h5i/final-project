package com.sparta.finalproject.recruitment.repository;

import com.sparta.finalproject.recruitment.dto.FindNewRecruitment;
import com.sparta.finalproject.recruitment.dto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.SearchRecruitment;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCustomRepository {

    Page<ResponseRecruitment> selectRecruitmentsBySearchCondition(Pageable pageable,
        SearchRecruitment searchRecruitment);

    List<FindNewRecruitment> findTop5ByOrderByIdDesc();
}
