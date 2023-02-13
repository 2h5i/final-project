package com.sparta.finalproject.recruitmentcomment.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.CreateRecruitmentComment;
import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import com.sparta.finalproject.recruitmentcomment.repository.RecruitmentCommentRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentCommentServiceImpl implements RecruitmentCommentService {

    private final RecruitmentCommentRepository recruitmentCommentRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public Long createRecruitmentComment(Long recruitmentId, CreateRecruitmentComment createComment,
        User user) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("게시물이 존재하지 않습니다.")
        );

        RecruitmentComment recruitmentComment = RecruitmentComment.builder()
            .content(createComment.getContent())
            .user(user)
            .recruitment(recruitment)
            .build();

        return recruitmentCommentRepository.save(recruitmentComment).getId();
    }
}
