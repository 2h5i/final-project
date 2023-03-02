package com.sparta.finalproject.bookmark.service;

import com.sparta.finalproject.bookmark.dto.BookmarkDto;
import com.sparta.finalproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkService {

    void createBookmark(Long recruitmentId, User user);

    void deleteBookmark(Long recruitmentId, User user);

    Page<BookmarkDto> selectMyBookmarks(Pageable pageable, Long userId);

    boolean selectCheckBookmark(Long recruitmentId, Long userId);
}
