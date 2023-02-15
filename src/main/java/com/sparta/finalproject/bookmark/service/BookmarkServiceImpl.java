package com.sparta.finalproject.bookmark.service;

import com.sparta.finalproject.bookmark.dto.BookmarkDto;
import com.sparta.finalproject.bookmark.entity.Bookmark;
import com.sparta.finalproject.bookmark.repository.BookmarkRepository;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.recruitment.repository.RecruitmentRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Recruitment recruitment = validateRecruitment(recruitmentId);

        validateBookmark(recruitmentId, user.getId());

        Bookmark bookmark = Bookmark.builder()
            .recruitment(recruitment)
            .user(user)
            .build();

        bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void deleteBookmark(Long recruitmentId, User user) {
        validateRecruitment(recruitmentId);

        Bookmark bookmark = bookmarkRepository.findByRecruitmentIdAndUserId(recruitmentId,
            user.getId()).orElseThrow(() -> new BadRequestException("북마크를 누르지 않았습니다."));

        bookmarkRepository.delete(bookmark);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookmarkDto> selectMyBookmarks(Pageable pageable, Long userId) {
        return bookmarkRepository.findByMyPageBookmark(pageable, userId);
    }

    private void validateBookmark(Long recruitmentId, Long userId) {
        if (bookmarkRepository.existsByRecruitmentIdAndUserId(recruitmentId, userId)) {
            throw new BadRequestException("이미 북마크 하였습니다.");
        }
    }

    private Recruitment validateRecruitment(Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId).orElseThrow(
            () -> new BadRequestException("해당 채용공고가 존재하지 않습니다.")
        );
    }
}
