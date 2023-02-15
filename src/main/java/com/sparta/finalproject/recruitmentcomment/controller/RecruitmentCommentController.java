package com.sparta.finalproject.recruitmentcomment.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentsDto;
import com.sparta.finalproject.recruitmentcomment.service.RecruitmentCommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitment-comments")
public class RecruitmentCommentController {

    private final RecruitmentCommentService recruitmentCommentService;

    @PostMapping("/{recruitmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Long createRecruitmentComment(@PathVariable Long recruitmentId,
        @RequestBody @Valid RecruitmentCommentDto.CreateRecruitmentComment createComment,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return recruitmentCommentService.createRecruitmentComment(recruitmentId, createComment,
            userDetails.getUser());
    }

    @PutMapping("/{recruitmentCommentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updateRecruitmentComment(@PathVariable Long recruitmentCommentId,
        @RequestBody @Valid RecruitmentCommentDto.CreateRecruitmentComment createComment,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recruitmentCommentService.updateRecruitmentComment(recruitmentCommentId, createComment,
            userDetails.getUser());
    }

    @DeleteMapping("/{recruitmentCommentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteRecruitmentComment(@PathVariable Long recruitmentCommentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recruitmentCommentService.deleteRecruitmentComment(recruitmentCommentId,
            userDetails.getUser());
    }

    @GetMapping("/{recruitmentId}")
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectRecruitmentCommentListByRecruitmentId(
        @PathVariable Long recruitmentId, @PageableDefault Pageable pageable) {

        return PageWrapper.of(recruitmentCommentService.selectRecruitmentCommentListByRecruitmentId
            (recruitmentId, pageable));
    }

    @GetMapping("/{userId}/my-page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public PageWrapper<RecruitmentCommentsDto> selectMyCommentLists(@PathVariable Long userId,
        @PageableDefault() Pageable pageable,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!userDetails.getUser().getId().equals(userId)) {
            throw new BadRequestException("본인 계정의 정보만 확인할 수 있습니다.");
        }
        return PageWrapper.of(recruitmentCommentService.selectMyCommentLists(pageable, userId));
    }
}
