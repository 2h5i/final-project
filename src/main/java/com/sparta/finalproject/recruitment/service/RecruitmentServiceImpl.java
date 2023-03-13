package com.sparta.finalproject.recruitment.service;

import com.sparta.finalproject.bookmark.repository.BookmarkRepository;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.recruitment.dto.FindNewRecruitment;
import com.sparta.finalproject.recruitment.dto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import com.sparta.finalproject.recruitmentcomment.repository.RecruitmentCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final BookmarkRepository bookmarkRepository;
    private final RecruitmentCommentRepository recruitmentCommentRepository;

    @Override
    @Transactional
    public Recruitment createRecruitment(String title, String subTitle, String content,
        String href) {
        Recruitment recruitment = Recruitment.builder()
            .title(title)
            .subTitle(subTitle)
            .content(content)
            .href(href)
            .build();

        return recruitmentRepository.save(recruitment);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseRecruitment selectRecruitmentById(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        return ResponseRecruitment.of(recruitment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseRecruitment> selectRecruitments(Pageable pageable,
        SearchRecruitment searchRecruitment) {
        return recruitmentRepository.selectRecruitmentsBySearchCondition(pageable,
            searchRecruitment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FindNewRecruitment> mainRecruitments() {
        return recruitmentRepository.findTop5ByOrderByIdDesc();
    }

    @Override
    @Transactional
    public void deleteRecruitmentAdmin(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("해당 채용공고가 존재하지 않습니다.")
        );

        recruitmentCommentRepository.deleteByRecruitmentQuery(recruitment);
        bookmarkRepository.deleteByBookmarkQuery(recruitment);
        recruitmentRepository.delete(recruitment);
    }

    @Override
    public boolean checkRecruitment(String href) {
        return recruitmentRepository.existsByHref(href);
    }
}
