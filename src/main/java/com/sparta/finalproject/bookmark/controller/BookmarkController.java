package com.sparta.finalproject.bookmark.controller;

import com.sparta.finalproject.bookmark.service.BookmarkService;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{recruitmentId}/bookmark")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void createBookmark(@PathVariable Long recruitmentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        bookmarkService.createBookmark(recruitmentId, userDetails.getUser());
    }

    @DeleteMapping("/{recruitmentId}/bookmark")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteBookmark(@PathVariable Long recruitmentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        bookmarkService.deleteBookmark(recruitmentId, userDetails.getUser());
    }
}
