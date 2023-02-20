package com.sparta.finalproject.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.auth.service.AuthService;
import com.sparta.finalproject.common.jwt.JwtUtil;
import com.sparta.finalproject.common.security.UserDetailsImpl;
import com.sparta.finalproject.user.entity.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  //로그아웃
  @GetMapping("/logout")
  public ResponseEntity logout(HttpServletRequest request) {
    // 정보를 컨트롤러로 보냈을 때 HttpServletRequest 객체 안에 모든 데이터가 들어가게 된다.

    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate(); // 등록된 세션을 삭제한다.
    }
    return new ResponseEntity("로그아웃 되었습니다.", HttpStatus.OK);
  }

  //회원 탈퇴
  @DeleteMapping("/delete")
  public void delete(@RequestBody AuthDto.DeleteRequestDto deleteRequestDto, UserDetailsImpl userDetails) {
    authService.delete(deleteRequestDto, userDetails.getUser());
  }

  //토큰 재발행
  @PostMapping("/reissue")
  public void reIssue(@RequestBody AuthDto.TokenDto tokenDto , HttpServletResponse response){
    authService.reIssue(tokenDto, response);
  }


  @GetMapping("/kakao-login")
  public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
    // code: 카카오 서버로부터 받은 인가 코드
    String createToken = authService.kakaoLogin(code, response);

    // Cookie 생성 및 직접 브라우저에 Set
    Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
    cookie.setPath("/");
    response.addCookie(cookie);
  }
}
