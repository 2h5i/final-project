package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.user.dto.ResponseUserAdmin;
import com.sparta.finalproject.user.dto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.SearchUserAdmin;
import com.sparta.finalproject.user.service.UserService;
import java.io.IOException;
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
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserAdminController {

    private final UserService userservice;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUserAdmin selectUserAdmin(@PathVariable Long userId) {
        return userservice.selectUserAdmin(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper<ResponseUserListAdmin> selectUsersAdmin(SearchUserAdmin searchUserAdmin,
        @PageableDefault Pageable pageable) {

        return PageWrapper.of(userservice.selectUsersAdmin(searchUserAdmin, pageable));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAdmin(@PathVariable Long userId) throws IOException {
        userservice.deleteUserAdmin(userId);
    }
}
