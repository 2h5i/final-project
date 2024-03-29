package com.sparta.finalproject.like.controller;

import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void createLike(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createLike(postId, userDetails.getUser());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Long selectLikeCount(@PathVariable Long postId) {

        return likeService.selectLikeCount(postId);
    }

    @GetMapping("/{postId}/check")
    public boolean selectLikeCheck(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return likeService.selectLikeCheck(postId, userDetails.getUser());
    }

    @DeleteMapping("/{postId}/unlike")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteLike(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteLike(postId, userDetails.getUser());
    }
}
