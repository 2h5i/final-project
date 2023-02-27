package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.dto.PostsDto;
import com.sparta.finalproject.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Query(
        nativeQuery = true,
        value = "SELECT p.title AS title, p.id AS postId FROM post p WHERE p.user_id = :userId"
    )
    Page<PostsDto> findMyPagePosts(Pageable pageable, @Param("userId") Long userId);


}
