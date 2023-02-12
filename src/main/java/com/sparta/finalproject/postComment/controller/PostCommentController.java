package com.sparta.finalproject.postComment.controller;

import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.postComment.dto.PostCommentDto;
import com.sparta.finalproject.postComment.service.PostCommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post-comments")
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Long createPostComment(@PathVariable Long postId,
        @RequestBody @Valid PostCommentDto.CreatePostComment createPostComment,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postCommentService.createPostComment(postId, createPostComment,
            userDetails.getUser());
    }
}