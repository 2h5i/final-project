package com.sparta.finalproject.bookmark.service;

import com.sparta.finalproject.user.entity.User;

public interface BookmarkService {

    void createBookmark(Long recruitmentId, User user);

    void deleteBookmark(Long recruitmentId, User user);
}
