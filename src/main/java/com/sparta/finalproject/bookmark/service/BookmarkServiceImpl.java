package com.sparta.finalproject.bookmark.service;

import com.sparta.finalproject.bookmark.entity.Bookmark;
import com.sparta.finalproject.bookmark.repository.BookmarkRepository;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final RecruitmentRepository recruitmentRepository;

    @Override
    @Transactional
    public void createBookmark(Long recruitmentId, User user) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("해당 채용공고가 존재하지 않습니다.")
        );

        validateBookmark(recruitmentId, user.getId());

        Bookmark bookmark = Bookmark.builder()
            .recruitment(recruitment)
            .user(user)
            .build();

        bookmarkRepository.save(bookmark);
    }

    private void validateBookmark(Long recruitmentId, Long userId) {
        if (bookmarkRepository.existsByRecruitmentIdAndUserId(recruitmentId, userId)) {
            throw new BadRequestException("이미 북마크 하였습니다.");
        }
    }
}
