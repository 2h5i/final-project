package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.common.file.FileService;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final FileService imageService;

    @PostMapping("/{userId}/image")
    public void imageUpload(@RequestParam MultipartFile file,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        imageService.upload(file, userDetails.getUser());
    }

    @PutMapping("/{userId}/image")
    public String updateProfileImage(@RequestParam MultipartFile file, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        imageService.updateProfileImage(file, userDetails.getUser());
        return "수정 완료";
    }

    @DeleteMapping("/{userId}/image")
    public String deleteProfileImage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        imageService.deleteProfileImage(userDetails.getUser());
        return "삭제 완료";
    }
}
