package com.sparta.finalproject.bookmark.repository;

import com.sparta.finalproject.bookmark.dto.BookmarkMyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkCustomRepository {

    Page<BookmarkMyPage> findByMyPageBookmark(Pageable pageable, Long userId);
}
