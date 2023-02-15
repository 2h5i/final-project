package com.sparta.finalproject.user.service;

import com.sparta.finalproject.user.entity.User;
import java.io.IOException;

public interface UserService {

    void updateProfileImage(String profileImage, User user) throws IOException;

}
