package com.sparta.finalproject.user.controller;

import com.sparta.finalproject.user.dto.UserDto;
import com.sparta.finalproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserAdminComtroller {

    private final UserService userservice;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.ResponseUserAdmin selectUserAdmin(@PathVariable Long userId) {
        return userservice.selectUserAdmin(userId);
    }
}
