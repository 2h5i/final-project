package com.sparta.finalproject.post.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.SearchPost;
import com.sparta.finalproject.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long createPost(@RequestBody @Valid PostDto.CreatePost createPost,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(createPost, userDetails.getUser());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto.ResponsePost getPostById(@PathVariable Long postId) {

        return postService.getPostById(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper getPosts(SearchPost searchPost,
        @PageableDefault() Pageable pageable) {
        return PageWrapper.of(postService.getPosts(pageable, searchPost));
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Long updatePost(@PathVariable Long postId,
        @RequestBody @Valid PostDto.UpdatePost updatePost,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.updatePost(postId, updatePost, userDetails.getUser());
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deletePost(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        postService.deletePost(postId, userDetails.getUser());
    }

}
