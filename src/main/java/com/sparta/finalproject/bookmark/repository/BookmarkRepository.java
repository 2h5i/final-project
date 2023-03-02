package com.sparta.finalproject.bookmark.repository;

import com.sparta.finalproject.bookmark.dto.BookmarkDto;
import com.sparta.finalproject.bookmark.entity.Bookmark;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

    Optional<Bookmark> findByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

    @Transactional
    @Modifying
    @Query("delete from Bookmark b where b.recruitment = :recruitment")
    void deleteByBookmarkQuery(@Param("recruitment") Recruitment recruitment);

    @Query(
        nativeQuery = true,
        value = "select r.title AS title, r.id AS recruitmentId "
            + "from bookmark b JOIN recruitment r on r.id = b.recruitment_id "
            + "where b.user_id = :userId"
    )
    Page<BookmarkDto> findByMyPageBookmark(Pageable pageable, @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("delete from Bookmark b where b.user.id =:userId ")
    void deleteByBookmark(@Param("userId") Long userId);
}
