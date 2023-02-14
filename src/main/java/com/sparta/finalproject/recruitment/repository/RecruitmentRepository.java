package com.sparta.finalproject.recruitment.repository;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>,
    RecruitmentCustomRepository {

    @Query(
        nativeQuery = true,
        value = "SELECT r.title , r.sub_title FROM recruitment r ORDER BY r.id DESC LIMIT 5"
    )
    List<Map<String, Object>> findTop5ByOrderByIdDesc();
}
