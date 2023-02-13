package com.sparta.finalproject.recruitmentcomment.service;

import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.CreateRecruitmentComment;
import com.sparta.finalproject.user.entity.User;

public interface RecruitmentCommentService {

    Long createRecruitmentComment(Long recruitmentId, CreateRecruitmentComment createComment,
        User user);
}
