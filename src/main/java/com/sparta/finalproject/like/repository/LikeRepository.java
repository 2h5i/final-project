package com.sparta.finalproject.like.repository;

import com.sparta.finalproject.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostIdAndUserId(Long postId, Long userId);

    Long countByPostId(Long postId);
}
