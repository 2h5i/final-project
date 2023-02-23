package com.sparta.finalproject.like.service;

import com.sparta.finalproject.user.entity.User;

public interface LikeService {

    void createLike(Long postId, User user);

    void deleteLike(Long postId, User user);

    Long selectLikeCount(Long postId);

    boolean selectLikeCheck(Long postId, User user);
}
