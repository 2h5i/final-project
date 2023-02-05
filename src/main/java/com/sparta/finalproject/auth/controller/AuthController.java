package com.sparta.finalproject.auth.controller;

import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.auth.service.AuthService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
