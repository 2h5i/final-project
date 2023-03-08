package com.sparta.finalproject.recruitmentcomment.service;

import com.sparta.finalproject.recruitmentcomment.dto.CreateRecruitmentComment;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentsDto;
import com.sparta.finalproject.recruitmentcomment.dto.ResponseRecruitmentCommentList;
import com.sparta.finalproject.recruitmentcomment.dto.SearchRecruitment;
import com.sparta.finalproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentCommentService {

    Long createRecruitmentComment(Long recruitmentId, CreateRecruitmentComment createComment,
        User user);

    Long updateRecruitmentComment(Long recruitmentCommentId, CreateRecruitmentComment createComment,
        User user);

    void deleteRecruitmentComment(Long recruitmentCommentId, User user);

    Page<ResponseRecruitmentCommentList>
    selectRecruitmentCommentListByRecruitmentId(Long recruitmentId, Pageable pageable);

    Page<ResponseRecruitmentCommentList>
    selectRecruitmentCommentListAdminByRecruitmentId(Pageable pageable,
        SearchRecruitment searchRecruitment);

    void deleteRecruitmentCommentAdmin(Long recruitmentId);

    Page<RecruitmentCommentsDto> selectMyCommentLists(Pageable pageable, Long userId);
}
