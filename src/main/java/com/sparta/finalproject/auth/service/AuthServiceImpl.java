package com.sparta.finalproject.auth.service;

import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.common.jwt.JwtUtil;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private static final String ADMIN_KEY = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

  @Override
  public void signup(AuthDto.SignupDto signupDto) {
    String userId = signupDto.getUserId();
    String password = passwordEncoder.encode(signupDto.getPassword());

    //회원중복확인
    Optional<User> found = userRepository.findByUserId(userId);

    if (found.isPresent()) {
      throw new IllegalArgumentException("중복된 username 입니다.");
    }

    String email = signupDto.getEmail();

    //사용자 Role 확인
    UserRole role = UserRole.USER;
    if (signupDto.isAdmin()) {
      if (!signupDto.getAdminKey().equals(ADMIN_KEY)) {
        throw new IllegalArgumentException("관리자 키가 틀려 등록이 불가능 합니다.");
      }
      role = UserRole.ADMIN;
    }

    User user = User.builder().userId(userId).password(password).email(email).role(role).build();

    userRepository.save(user);
  }

  @Override
  public void login(AuthDto.LoginDto loginDto, HttpServletResponse response) {
    String userId = loginDto.getUserId();
    String password = loginDto.getPassword();

    User user = userRepository.findByUserId(userId).orElseThrow(
        () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
    );

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    addTokenToHeader(response, user);
  }

  private void addTokenToHeader(HttpServletResponse response, User user) {
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
        jwtUtil.createToken(user.getUserId(), user.getRole()));
  }
}
