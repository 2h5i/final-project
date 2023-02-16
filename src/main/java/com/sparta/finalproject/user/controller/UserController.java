package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.user.dto.UserDto;
import com.sparta.finalproject.user.service.UserService;
import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/{userId}/image")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateUserProfileImage(@PathVariable Long userId,
        @RequestParam("profileImage") MultipartFile multipartFile,
        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        return userService.updateProfileImage(userId, multipartFile, userDetails.getUser());
    }

    @DeleteMapping("/{userId}/image")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteUserProfileImage(@PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        userService.deleteProfileImage(userId, userDetails.getUser());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Long updateUser(@PathVariable Long userId, @RequestBody @Valid UserDto.RequestUpdateUser
        updateUser, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!userDetails.getUser().getId().equals(userId)) {
            throw new BadRequestException("정보 수정에 대한 권한이 없습니다.");
        }
        userService.updateUser(updateUser, userId);
        return userId;
    }

    @GetMapping("/{userId}/my-page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDto.ResponseUser selectMyPage(@PathVariable Long userId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!userDetails.getUser().getId().equals(userId)) {
            throw new BadRequestException("정보 수정에 대한 권한이 없습니다.");
        }
        return userService.selectMyPage(userId, userDetails.getUser());
    }

}
