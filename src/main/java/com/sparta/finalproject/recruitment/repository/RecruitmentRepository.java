package com.sparta.finalproject.recruitment.repository;

import com.sparta.finalproject.recruitment.dto.RecruitmentDto.ResponseRecruitment;import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Page<ResponseRecruitment> getRecruitmentsBySearchConditin (Pageable pageable,
        SearchRecruitment searchRecruitment);
}
