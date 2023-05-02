package com.sparta.finalproject.user.service;

import com.sparta.finalproject.bookmark.repository.BookmarkRepository;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.common.s3.S3Upload;
import com.sparta.finalproject.like.repository.LikeRepository;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.postcomment.repository.PostCommentRepository;
import com.sparta.finalproject.recruitmentcomment.repository.RecruitmentCommentRepository;
import com.sparta.finalproject.user.dto.RequestUpdateUser;
import com.sparta.finalproject.user.dto.ResponseUser;
import com.sparta.finalproject.user.dto.ResponseUserAdmin;
import com.sparta.finalproject.user.dto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.SearchUserAdmin;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.repository.UserRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final S3Upload s3Upload;
    private final PostRepository postRepository;

    private final PostCommentRepository postCommentRepository;
    private final RecruitmentCommentRepository recruitmentCommentRepository;

    private final BookmarkRepository bookmarkRepository;

    private final LikeRepository likeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String updateProfileImage(MultipartFile profileImage, User user)
        throws IOException {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new BadRequestException("사용자의 정보가 존재하지 않습니다.")
        );

        findUser.validateUser(user);

        if (!isEmptyImage(user.getProfileImage())) {
            s3Upload.deleteFile(user.getProfileImage());
        }

        String profileImageUrl = s3Upload.upload(profileImage);

        findUser.updateProfileImage(profileImageUrl);

        userRepository.save(findUser);

        return profileImageUrl;
    }

    @Override
    @Transactional
    public void updateUser(RequestUpdateUser updateUser, User user) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new BadRequestException("회원 정보가 존재하지 않습니다.")
        );
        String password = passwordEncoder.encode(updateUser.getPassword());
        findUser.updateUser(password);
        userRepository.saveAndFlush(findUser);
    }

    @Override
    @Transactional
    public void deleteProfileImage(User user) throws IOException {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new BadRequestException("사용자의 정보가 존재하지 않습니다.")
        );

        findUser.validateUser(user);

        s3Upload.deleteFile(findUser.getProfileImage());
        findUser.deleteProfileImage();

        userRepository.save(findUser);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseUser selectMyPage(User user) {
        ResponseUser myPage = ResponseUser.builder()
            .userId(user.getUserId())
            .email(user.getEmail())
            .profileImage(user.getProfileImage())
            .build();

        return myPage;
    }

    @Override
    public ResponseUserAdmin selectUserAdmin(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(
            () -> new BadRequestException("사용자의 정보가 존재하지 않습니다.")
        );
        ResponseUserAdmin myPage = ResponseUserAdmin.builder()
            .id(findUser.getId())
            .userId(findUser.getUserId())
            .kakaoId(findUser.getKakaoId())
            .email(findUser.getEmail())
            .profileImage(findUser.getProfileImage())
            .build();

        return myPage;
    }

    @Override
    public Page<ResponseUserListAdmin> selectUsersAdmin(SearchUserAdmin searchUserAdmin,
        Pageable pageable) {

        return userRepository.findUsersBySearchConditionAdmin(searchUserAdmin, pageable);
    }

    @Override
    @Transactional
    public void deleteUserAdmin(Long userId) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException("사용자를 찾을 수 없습니다."));
        likeRepository.deleteByLike(userId);
        postCommentRepository.deleteByPostComment(userId);
        postRepository.deleteByPost(userId);
        bookmarkRepository.deleteByBookmark(userId);
        recruitmentCommentRepository.deleteByRecruitmentComment(userId);
        if (!isEmptyImage(user.getProfileImage())) {
            s3Upload.deleteFile(user.getProfileImage());
        }
        userRepository.delete(user);
    }

    private boolean isEmptyImage(String image) {
        return image == null || image.isEmpty();
    }

    @Override
    public boolean checkUserIdDuplicate(String userId) {

        return userRepository.existsByUserId(userId); // 중복이면 true, 중복되지 않으면 false
    }

}
