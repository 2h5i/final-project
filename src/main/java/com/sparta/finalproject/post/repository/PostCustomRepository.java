package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.dto.MyPagePost;
import com.sparta.finalproject.post.dto.ResponsePost;
import com.sparta.finalproject.post.dto.ResponsePostList;
import com.sparta.finalproject.post.dto.SearchPost;
import com.sparta.finalproject.post.dto.SearchPostAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {

    Page<ResponsePostList> getPostsBySearchCondition(Pageable pageable, SearchPost searchPost);

    Page<ResponsePost> selectPostsBySearchConditionAdmin(SearchPostAdmin searchPostAdmin,
        Pageable pageable);

    Page<MyPagePost> findByMyPagePosts(Pageable pageable, Long userId);
}
