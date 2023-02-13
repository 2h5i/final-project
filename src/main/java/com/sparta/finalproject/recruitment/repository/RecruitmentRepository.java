package com.sparta.finalproject.recruitment.repository;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>,
    RecruitmentCustomRepository {

    Page<Recruitment> findById(long id, Pageable pageable);
}
