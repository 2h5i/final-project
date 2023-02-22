package com.sparta.finalproject.user.service;

import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.user.dto.UserDto;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserAdmin;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.UserDto.SearchUserAdmin;
import com.sparta.finalproject.user.entity.User;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String updateProfileImage(Long userId, MultipartFile multipartFile, User user)
        throws IOException;

    void updateUser(UserDto.RequestUpdateUser updateUser, Long userId);

    void deleteProfileImage(Long userId, User user) throws IOException;

    UserDto.ResponseUser selectMyPage(Long userId, User user);

    ResponseUserAdmin selectUserAdmin(Long userId);

    void deleteUserAdmin(Long userId);
    Page<ResponseUserListAdmin> selectUsersAdmin(SearchUserAdmin searchUserAdmin,
        Pageable pageable);
}
