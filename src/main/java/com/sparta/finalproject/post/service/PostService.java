package com.sparta.finalproject.post.service;

import com.sparta.finalproject.post.dto.PostDto.CreatePost;
import com.sparta.finalproject.user.entity.User;

public interface PostService {

    Long createPost(CreatePost createPost, User user);
}
