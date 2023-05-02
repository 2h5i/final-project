package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.user.dto.RequestUpdateUser;
import com.sparta.finalproject.user.dto.ResponseUser;
import com.sparta.finalproject.user.service.UserService;
import java.io.IOException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateUserProfileImage(@RequestParam("profileImage") MultipartFile multipartFile,
        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        return userService.updateProfileImage(multipartFile, userDetails.getUser());
    }

    @DeleteMapping("/image")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteUserProfileImage(@AuthenticationPrincipal UserDetailsImpl userDetails)
        throws IOException {
        userService.deleteProfileImage(userDetails.getUser());
    }

    @PutMapping("/my-page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updateUser(@RequestBody @Valid RequestUpdateUser
        updateUser, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updateUser(updateUser, userDetails.getUser());
    }

    @GetMapping("/my-page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseUser selectMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return userService.selectMyPage(userDetails.getUser());
    }

    //아이디 중복체크
    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {

        return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
    }

}
