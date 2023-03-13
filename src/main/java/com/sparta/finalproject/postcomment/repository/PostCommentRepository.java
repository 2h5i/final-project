package com.sparta.finalproject.postcomment.repository;

import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.postcomment.entity.PostComment;
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

    @Transactional
    @Modifying
    @Query("delete from PostComment pc where pc.user.id =:userId ")
    void deleteByPostComment(@Param("userId") Long userId);

}
