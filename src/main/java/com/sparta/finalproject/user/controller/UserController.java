package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.user.service.UserService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateUserProfileImage(@RequestParam("profileImage") MultipartFile multipartFile,
        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        return userService.updateProfileImage(multipartFile, userDetails.getUser());
    }
}
