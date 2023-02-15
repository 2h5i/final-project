package com.sparta.finalproject.user.service;

import com.sparta.finalproject.common.s3.S3Upload;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.repository.UserRepository;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final S3Upload s3Upload;

    @Override
    @Transactional
    public String updateProfileImage(MultipartFile profileImage, User user) throws IOException {
        if (Objects.nonNull(user.getProfileImage())) {
            s3Upload.deleteFile(user.getProfileImage());
        }

        String profileImageUrl = s3Upload.upload(profileImage);

        user.updateProfileImage(profileImageUrl);

        userRepository.save(user);

        return profileImageUrl;
    }
}
