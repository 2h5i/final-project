package com.sparta.finalproject.postcomment.repository;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.postcomment.dto.PostCommentsDto;
import com.sparta.finalproject.postcomment.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>,
    PostCommentCustomRepository {

    @Transactional
    @Modifying
    @Query("delete from PostComment pc where pc.post = :post")
    void deleteByPostQuery(@Param("post") Post post);

    @Query(
        nativeQuery = true,
        value = "select p.id AS postId, pc.content AS content "
            + "FROM post_comment pc JOIN post p on p.id = pc.post_id "
            + "where pc.user_id = :userId"
    )
    Page<PostCommentsDto> findByMyPageComment(Pageable pageable, @Param("userId") Long userId);
}
