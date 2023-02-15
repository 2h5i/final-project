package com.sparta.finalproject.post.service;

import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.SearchPost;
import com.sparta.finalproject.post.dto.PostDto.SearchPostAdmin;
import com.sparta.finalproject.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long createPost(PostDto.CreatePost createPost, User user);

    PostDto.ResponsePost getPostById(Long postId);

    Long updatePost(Long postId, PostDto.UpdatePost updatePost, User user);

    void deletePost(Long postId, User user);

    Page<PostDto.ResponsePostList> getPosts(Pageable pageable, SearchPost searchPost);

    void deletePostAdmin(Long postId);

    Page<PostDto.ResponsePost> selectPostsAdmin(SearchPostAdmin searchPostAdmin,
        Pageable pageable);
}
