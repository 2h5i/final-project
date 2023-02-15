package com.sparta.finalproject.bookmark.controller;

import com.sparta.finalproject.bookmark.dto.BookmarkDto;
import com.sparta.finalproject.bookmark.service.BookmarkService;
import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.common.security.UserDetailsImpl;
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

    @GetMapping("/{userId}/my-page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public PageWrapper<BookmarkDto> selectMyBookmarks(@PathVariable Long userId,
        @PageableDefault() Pageable pageable,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!userDetails.getUser().getId().equals(userId)) {
            throw new BadRequestException("본인 계정의 정보만 확인할 수 있습니다.");
        }
        return PageWrapper.of(bookmarkService.selectMyBookmarks(pageable, userId));
    }
}
