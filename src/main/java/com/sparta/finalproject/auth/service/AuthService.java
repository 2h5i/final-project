package com.sparta.finalproject.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.DeleteRequestDto;
import com.sparta.finalproject.auth.dto.LoginDto;
import com.sparta.finalproject.auth.dto.SignupDto;
import com.sparta.finalproject.auth.dto.TokenDto;
import com.sparta.finalproject.user.entity.User;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    void signup(SignupDto signupDto);

    void login(LoginDto loginDto, HttpServletResponse response);

    void logout(TokenDto tokenDto);

    void delete(DeleteRequestDto deleteRequestDto, User user);

    void reIssue(TokenDto tokenDto, HttpServletResponse response);

    void sendSimpleMessage(String to) throws Exception;

    String kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException;
}
