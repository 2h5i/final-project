package com.sparta.finalproject.recruitment.controller;

import com.sparta.finalproject.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/recruitments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RecruitmentAdminController {

    private final RecruitmentService recruitmentService;

    @DeleteMapping("/{recruitmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRecruitmentAdmin(@PathVariable Long recruitmentId) {
        recruitmentService.deleteRecruitmentAdmin(recruitmentId);
    }
}