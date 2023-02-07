package com.sparta.finalproject.post.controller;

import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Long createPost(@RequestBody PostDto.CreatePost createPost,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(createPost, userDetails.getUser());
    }

}
