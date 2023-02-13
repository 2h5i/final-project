package com.sparta.finalproject.postcomment.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.postcomment.dto.PostCommentDto;
import com.sparta.finalproject.postcomment.service.PostCommentService;
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

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectPostCommentListByPostId(
        @PathVariable Long postId, @PageableDefault Pageable pageable) {

        return PageWrapper.of(postCommentService.selectPostCommentListByPostId(postId, pageable));
    }

    @PutMapping("/{postCommentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updatePostByPostCommentId(@PathVariable Long postCommentId,
        @RequestBody @Valid PostCommentDto.UpdatePostComment updatePostComment,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        postCommentService.updatePostByPostCommentId(postCommentId, updatePostComment,
            userDetails.getUser());
    }

    @DeleteMapping("/{postCommentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deletePostByPostCommentId(@PathVariable Long postCommentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postCommentService.deletePostByCommentId(postCommentId, userDetails.getUser());
    }
}
