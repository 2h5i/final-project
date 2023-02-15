package com.sparta.finalproject.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.AuthDto;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

  void signup(AuthDto.SignupDto signupDto);

  void login(AuthDto.LoginDto loginDto, HttpServletResponse response);

  String kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException;
}
