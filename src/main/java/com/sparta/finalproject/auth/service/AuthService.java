package com.sparta.finalproject.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.auth.dto.AuthDto.DeleteRequestDto;
import com.sparta.finalproject.user.entity.User;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    void signup(AuthDto.SignupDto signupDto);

    void login(AuthDto.LoginDto loginDto, HttpServletResponse response);

    void logout(AuthDto.TokenDto tokenDto);

    void delete(DeleteRequestDto deleteRequestDto, User user);

    void reIssue(AuthDto.TokenDto tokenDto, HttpServletResponse response);

    void sendSimpleMessage(String to) throws Exception;

    String kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException;
}
