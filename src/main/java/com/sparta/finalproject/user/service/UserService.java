package com.sparta.finalproject.user.service;

import com.sparta.finalproject.user.dto.RequestUpdateUser;
import com.sparta.finalproject.user.dto.ResponseUser;
import com.sparta.finalproject.user.dto.ResponseUserAdmin;
import com.sparta.finalproject.user.dto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.SearchUserAdmin;
import com.sparta.finalproject.user.entity.User;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    String updateProfileImage(MultipartFile multipartFile, User user)
        throws IOException;

    void updateUser(RequestUpdateUser updateUser, User user);

    void deleteProfileImage(User user) throws IOException;

    ResponseUser selectMyPage(User user);

    ResponseUserAdmin selectUserAdmin(Long userId);

    void deleteUserAdmin(Long userId) throws IOException;

    boolean checkUserIdDuplicate(String userId);

    Page<ResponseUserListAdmin> selectUsersAdmin(SearchUserAdmin searchUserAdmin,
        Pageable pageable);
}
