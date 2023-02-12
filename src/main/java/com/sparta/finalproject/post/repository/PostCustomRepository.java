package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {

    Page<PostDto.ResponsePostList> getPostsBySearchCondition(Pageable pageable,
        PostDto.SearchPost searchPost);
}
