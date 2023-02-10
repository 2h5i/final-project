package com.sparta.finalproject.like.service;

import com.sparta.finalproject.user.entity.User;

public interface LikeService {

    void createLike(Long postId, User user);
}
