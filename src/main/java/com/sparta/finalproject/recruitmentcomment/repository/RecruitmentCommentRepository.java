package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentCommentRepository extends JpaRepository<RecruitmentComment, Long>,
    RecruitmentCommentCustomRepository {

}
