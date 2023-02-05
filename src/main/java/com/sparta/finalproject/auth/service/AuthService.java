package com.sparta.finalproject.auth.service;

import com.sparta.finalproject.auth.dto.AuthDto;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

  void signup(AuthDto.SignupDto signupDto);

  void login(AuthDto.LoginDto loginDto, HttpServletResponse response);

}
