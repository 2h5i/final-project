package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Override
    @Transactional
    public void createRecruitment(String title, String subTitle, String content, String href) {
        Recruitment recruitment = Recruitment.builder()
            .title(title)
            .subTitle(subTitle)
            .content(content)
            .href(href)
            .build();

        recruitmentRepository.saveAndFlush(recruitment);
    }

    @Override
    @Transactional(readOnly = true)
    public RecruitmentDto.ResponseRecruitment selectRecruitmentById(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        return RecruitmentDto.ResponseRecruitment.of(recruitment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseRecruitment> selectRecruitments(Pageable pageable,
        SearchRecruitment searchRecruitment) {
        return recruitmentRepository.selectRecruitmentsBySearchCondition(pageable,
            searchRecruitment);
    }
}
