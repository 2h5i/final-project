package com.sparta.finalproject.user.service;

import com.sparta.finalproject.user.dto.UserDto;
import com.sparta.finalproject.user.entity.User;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String updateProfileImage(MultipartFile multipartFile, User user) throws IOException;

    void updateUser(UserDto.UpdateUser updateUser, Long userId);
}
