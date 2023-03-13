package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Transactional
    @Modifying
    @Query("delete from Post p where p.user.id =:userId ")
    void deleteByPost(@Param("userId") Long userId);
}
