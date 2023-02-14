package com.sparta.finalproject.recruitmentcomment.controller;

import com.sparta.finalproject.recruitmentcomment.service.RecruitmentCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/recruitment-comments")
public class RecruitmentCommentAdminController {

    private final RecruitmentCommentService recruitmentCommentService;

    @DeleteMapping
    @RequestMapping("/{recruitmentCommentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRecruitmentCommentAdmin(@PathVariable Long recruitmentCommentId) {
        recruitmentCommentService.deleteRecruitmentCommentAdmin(recruitmentCommentId);
    }


}
