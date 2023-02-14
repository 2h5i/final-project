package com.sparta.finalproject.common.file;


import com.sparta.finalproject.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void upload(MultipartFile file, User user);
    void updateProfileImage(MultipartFile file, User user);

    void deleteProfileImage(User user);



}

