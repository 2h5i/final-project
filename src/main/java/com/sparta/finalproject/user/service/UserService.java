package com.sparta.finalproject.user.service;

import com.sparta.finalproject.user.dto.UserDto;
import com.sparta.finalproject.user.entity.User;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String updateProfileImage(Long userId, MultipartFile multipartFile, User user)
        throws IOException;

    void updateUser(UserDto.UpdateUser updateUser, Long userId);

    void deleteProfileImage(Long userId, User user) throws IOException;

}
