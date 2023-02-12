package com.sparta.finalproject.postComment.repository;

import com.sparta.finalproject.postComment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

}
