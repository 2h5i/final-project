package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Override
    @Transactional
    public void createRecruitment(String title, String subTitle, String content, String href){
        Recruitment recruitment = Recruitment.builder()
            .title(title)
            .subTitle(subTitle)
            .content(content)
            .href(href)
            .build();

        recruitmentRepository.save(recruitment);
    }
}
