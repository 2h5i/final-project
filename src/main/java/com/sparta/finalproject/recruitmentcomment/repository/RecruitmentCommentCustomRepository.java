package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.ResponseRecruitmentCommentList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCommentCustomRepository {

    Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListByRecruitmentId
        (Long recruitmentId, Pageable pageable);
}
