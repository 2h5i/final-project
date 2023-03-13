package com.sparta.finalproject.recruitmentcomment.repository;

import com.sparta.finalproject.recruitmentcomment.dto.MyPageRecruitmentComments;
import com.sparta.finalproject.recruitmentcomment.dto.ResponseRecruitmentCommentList;
import com.sparta.finalproject.recruitmentcomment.dto.SearchRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCommentCustomRepository {

    Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListByRecruitmentId
        (Long recruitmentId, Pageable pageable);

    Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListAdminByRecruitmentId
        (Pageable pageable, SearchRecruitment searchRecruitment);

    Page<MyPageRecruitmentComments> findMyPageRecruitmentComments(Pageable pageable, Long userId);
}
