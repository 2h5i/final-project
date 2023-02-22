package com.sparta.finalproject.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.auth.service.AuthService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid AuthDto.SignupDto signupDto) {
        authService.signup(signupDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthDto.LoginDto loginDto, HttpServletResponse response) {
        authService.login(loginDto, response);
    }

    @GetMapping("/kakao-login")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response)
        throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = authService.kakaoLogin(code, response);

        return createToken;
    }
}
