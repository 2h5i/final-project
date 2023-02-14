package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.ResponseRecruitmentCommentList;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.SearchRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCommentCustomRepository {

    Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListByRecruitmentId
        (Long recruitmentId, Pageable pageable);

    Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListAdminByRecruitmentId
        (Pageable pageable, SearchRecruitment searchRecruitment);
}
