package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RecruitmentCommentRepository extends JpaRepository<RecruitmentComment, Long>,
    RecruitmentCommentCustomRepository {

    @Transactional
    @Modifying
    @Query("delete from RecruitmentComment rc where rc.recruitment = :recruitment")
    void deleteByRecruitmentQuery(@Param("recruitment") Recruitment recruitment);

    @Transactional
    @Modifying
    @Query("delete from RecruitmentComment bc where bc.user.id =:userId ")
    void deleteByRecruitmentComment(@Param("userId") Long userId);

}
