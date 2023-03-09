package com.sparta.finalproject.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.DeleteRequestDto;
import com.sparta.finalproject.auth.dto.LoginDto;
import com.sparta.finalproject.auth.dto.SignupDto;
import com.sparta.finalproject.auth.dto.TokenDto;
import com.sparta.finalproject.auth.service.AuthService;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupDto signupDto) {
        authService.signup(signupDto);
    }

    //로그인
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        authService.login(loginDto, response);
    }


    //로그아웃
    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(final @RequestBody TokenDto tokenDto) {
        authService.logout(tokenDto);
    }

    //회원 탈퇴
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteRequestDto deleteRequestDto,
        UserDetailsImpl userDetails) {
        authService.delete(deleteRequestDto, userDetails.getUser());
    }

    //토큰 재발행
    @PostMapping("/reissue")
    public void reIssue(@RequestBody TokenDto tokenDto, HttpServletResponse response) {
        authService.reIssue(tokenDto, response);
    }


    //카카오 로그인
    @GetMapping("/kakao-login")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response)
        throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = authService.kakaoLogin(code, response);

        return createToken;
    }

    //이메일 인증
    @PostMapping("/emailConfirm")
    public void emailConfirm(@RequestParam String email) throws Exception {

        authService.sendSimpleMessage(email);

    }

}
