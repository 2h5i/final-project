package com.sparta.finalproject.post.service;

import com.sparta.finalproject.post.dto.CreatePost;
import com.sparta.finalproject.post.dto.MyPagePost;
import com.sparta.finalproject.post.dto.ResponsePost;
import com.sparta.finalproject.post.dto.ResponsePostList;
import com.sparta.finalproject.post.dto.SearchPost;
import com.sparta.finalproject.post.dto.SearchPostAdmin;
import com.sparta.finalproject.post.dto.UpdatePost;
import com.sparta.finalproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long createPost(CreatePost createPost, User user);

    ResponsePost getPostById(Long postId);

    Long updatePost(Long postId, UpdatePost updatePost, User user);

    void deletePost(Long postId, User user);

    Page<ResponsePostList> getPosts(Pageable pageable, SearchPost searchPost);

    void deletePostAdmin(Long postId);

    Page<ResponsePost> selectPostsAdmin(SearchPostAdmin searchPostAdmin,
        Pageable pageable);

    Page<MyPagePost> selectMyPostLists(Pageable pageable, Long userId);
}
