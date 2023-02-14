package com.sparta.finalproject.bookmark.repository;

import com.sparta.finalproject.bookmark.entity.Bookmark;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

    Optional<Bookmark> findByRecruitmentIdAndUserId(Long recruitmentId, Long userId);

}
