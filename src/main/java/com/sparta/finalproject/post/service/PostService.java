package com.sparta.finalproject.post.service;

import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.user.entity.User;

public interface PostService {

    Long createPost(PostDto.CreatePost createPost, User user);

    PostDto.ResponsePost getPostById(Long postId);
}
