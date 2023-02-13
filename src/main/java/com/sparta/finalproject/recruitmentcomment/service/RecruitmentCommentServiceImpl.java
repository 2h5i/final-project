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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentCommentServiceImpl implements RecruitmentCommentService {

    private final RecruitmentCommentRepository recruitmentCommentRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Override
    @Transactional
    public Long createRecruitmentComment(Long recruitmentId, CreateRecruitmentComment createComment,
        User user) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("댓글을 작성할 게시물이 존재하지 않습니다.")
        );

        RecruitmentComment recruitmentComment = RecruitmentComment.builder()
            .content(createComment.getContent())
            .user(user)
            .recruitment(recruitment)
            .build();

        return recruitmentCommentRepository.save(recruitmentComment).getId();
    }

    @Override
    @Transactional
    public Long updateRecruitmentComment(Long recruitmentCommentId,
        CreateRecruitmentComment createComment, User user) {
        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(
            recruitmentCommentId).orElseThrow(
            () -> new BadRequestException("수정 할 댓글이 존재하지 않습니다.")
        );

        recruitmentComment.validateUser(user);
        recruitmentComment.editComment(createComment.getContent());

        recruitmentCommentRepository.saveAndFlush(recruitmentComment);

        return recruitmentCommentId;
    }

    @Override
    @Transactional
    public void deleteRecruitmentComment(Long recruitmentCommentId, User user) {
        RecruitmentComment recruitmentComment = recruitmentCommentRepository.findById(
            recruitmentCommentId).orElseThrow(
            () -> new BadRequestException("수정 할 댓글이 존재하지 않습니다.")
        );

        recruitmentComment.validateUser(user);

        recruitmentCommentRepository.delete(recruitmentComment);
    }
}
