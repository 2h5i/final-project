package com.sparta.finalproject.recruitment.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.recruitment.dto.FindNewRecruitment;
import com.sparta.finalproject.recruitment.dto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.SearchRecruitment;
import com.sparta.finalproject.recruitment.service.RecruitmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@EnableScheduling
@Slf4j
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping("/{recruitmentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseRecruitment selectRecruitmentById(@PathVariable Long
        recruitmentId) {

        return recruitmentService.selectRecruitmentById(recruitmentId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectRecruitments(SearchRecruitment searchRecruitment,
        @PageableDefault() Pageable pageable) {

        return PageWrapper.of(recruitmentService.selectRecruitments(pageable, searchRecruitment));
    }

    @GetMapping("/main")
    @ResponseStatus(HttpStatus.OK)
    public List<FindNewRecruitment> mainRecruitments() {

        return recruitmentService.mainRecruitments();
    }
}

