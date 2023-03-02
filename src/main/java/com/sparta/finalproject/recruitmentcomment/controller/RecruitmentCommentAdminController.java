package com.sparta.finalproject.recruitmentcomment.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.SearchRecruitment;
import com.sparta.finalproject.recruitmentcomment.service.RecruitmentCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/recruitment-comments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RecruitmentCommentAdminController {

    private final RecruitmentCommentService recruitmentCommentService;

    @DeleteMapping(("/{recruitmentCommentId}"))
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRecruitmentCommentAdmin(@PathVariable Long recruitmentCommentId) {
        recruitmentCommentService.deleteRecruitmentCommentAdmin(recruitmentCommentId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectRecruitmentCommentListByRecruitmentId(SearchRecruitment
        searchRecruitment, @PageableDefault Pageable pageable) {

        return PageWrapper.of(recruitmentCommentService.
            selectRecruitmentCommentListAdminByRecruitmentId(pageable, searchRecruitment));
    }
}
