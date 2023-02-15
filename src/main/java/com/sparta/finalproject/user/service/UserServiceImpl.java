package com.sparta.finalproject.user.service;

import com.sparta.finalproject.common.s3.S3Upload;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.repository.UserRepository;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final S3Upload s3Upload;

    @Override
    public void updateProfileImage(String profileImage, User user) throws IOException {
        if (Objects.nonNull(user.getProfileImage())) {
            System.out.println("삭제하기");
            s3Upload.deleteFile(user.getProfileImage());
        }

        user.updateProfileImage(profileImage);

        userRepository.save(user);
    }
}
