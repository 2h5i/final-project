package com.sparta.finalproject.postcomment.repository;

import com.sparta.finalproject.postcomment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

}
