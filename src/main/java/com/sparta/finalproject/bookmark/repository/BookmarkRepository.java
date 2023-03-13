package com.sparta.finalproject.bookmark.repository;

import com.sparta.finalproject.bookmark.entity.Bookmark;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>,
    BookmarkCustomRepository {

    boolean existsByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

    Optional<Bookmark> findByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

    @Transactional
    @Modifying
    @Query("delete from Bookmark b where b.recruitment = :recruitment")
    void deleteByBookmarkQuery(@Param("recruitment") Recruitment recruitment);

    @Transactional
    @Modifying
    @Query("delete from Bookmark b where b.user.id =:userId ")
    void deleteByBookmark(@Param("userId") Long userId);
}
