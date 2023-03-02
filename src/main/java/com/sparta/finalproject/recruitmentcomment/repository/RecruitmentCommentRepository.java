package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentsDto;
import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Query(
        nativeQuery = true,
        value = "SELECT rc.recruitment_id AS recruitmentId, rc.content AS content "
            + "FROM recruitment_comment rc JOIN recruitment r on rc.recruitment_id = r.id "
            + "where rc.user_id = :userId"
    )
    Page<RecruitmentCommentsDto> findMyPageRecruitmentComments(Pageable pageable,
        @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("delete from RecruitmentComment bc where bc.user.id =:userId ")
    void deleteByRecruitmentComment(@Param("userId") Long userId);

}
