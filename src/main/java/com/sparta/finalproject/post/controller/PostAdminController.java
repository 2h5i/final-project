package com.sparta.finalproject.post.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.post.dto.PostDto.ResponsePost;
import com.sparta.finalproject.post.dto.PostDto.SearchPostAdmin;
import com.sparta.finalproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/posts")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PostAdminController {

    private final PostService postService;

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePost selectPostAdmin(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectPostsAdmin(SearchPostAdmin searchPostAdmin,
        @PageableDefault Pageable pageable) {
        return PageWrapper.of(postService.selectPostsAdmin(searchPostAdmin, pageable));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePostAdmin(@PathVariable Long postId) {
        postService.deletePostAdmin(postId);
    }

}
