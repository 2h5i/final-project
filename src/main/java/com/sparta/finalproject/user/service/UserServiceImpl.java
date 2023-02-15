package com.sparta.finalproject.user.service;

import com.sparta.finalproject.common.exception.BadRequestException;
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
    public String updateProfileImage(Long userId, MultipartFile profileImage, User user)
        throws IOException {
        User findUser = userRepository.findById(userId).orElseThrow(
            () -> new BadRequestException("사용자의 정보가 존재하지 않습니다.")
        );

        findUser.validateUser(user);

        if (Objects.nonNull(user.getProfileImage())) {
            s3Upload.deleteFile(user.getProfileImage());
        }

        String profileImageUrl = s3Upload.upload(profileImage);

        findUser.updateProfileImage(profileImageUrl);

        userRepository.save(findUser);

        return profileImageUrl;
    }

    @Override
    public void deleteProfileImage(Long userId, User user) throws IOException {
        User findUser = userRepository.findById(userId).orElseThrow(
            () -> new BadRequestException("사용자의 정보가 존재하지 않습니다.")
        );

        findUser.validateUser(user);

        s3Upload.deleteFile(findUser.getProfileImage());
        findUser.deleteProfileImage();

        userRepository.save(findUser);
    }
}
