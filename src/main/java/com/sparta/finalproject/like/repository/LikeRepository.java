package com.sparta.finalproject.like.repository;

import com.sparta.finalproject.like.entity.Like;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostIdAndUserId(Long postId, Long userId);

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    Long countByPostId(Long postId);

    boolean existsByPostIdAndUser(Long postId, User user);

    @Transactional
    @Modifying
    @Query("delete from Like l where l.post = :post")
    void deleteByPostQuery(@Param("post") Post post);
}
