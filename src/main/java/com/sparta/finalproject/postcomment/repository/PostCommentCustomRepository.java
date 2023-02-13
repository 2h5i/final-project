package com.sparta.finalproject.postcomment.repository;

import com.sparta.finalproject.postcomment.dto.PostCommentDto.ResponsePostCommentList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCommentCustomRepository {

    Page<ResponsePostCommentList> selectPostCommentListByPostId(Long postId, Pageable pageable);
}
