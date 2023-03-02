package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.ResponsePost;
import com.sparta.finalproject.post.dto.PostDto.SearchPostAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {

    Page<PostDto.ResponsePostList> getPostsBySearchCondition(Pageable pageable,
        PostDto.SearchPost searchPost);

    Page<ResponsePost> selectPostsBySearchConditionAdmin(SearchPostAdmin searchPostAdmin,
        Pageable pageable);
}
