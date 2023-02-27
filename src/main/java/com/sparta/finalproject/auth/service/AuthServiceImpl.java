package com.sparta.finalproject.auth.service;


import static com.sparta.finalproject.common.jwt.JwtUtil.AUTHORIZATION_HEADER;
import static com.sparta.finalproject.common.jwt.JwtUtil.REFRESH_TOKEN_VALID_TIME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.finalproject.auth.dto.AuthDto;
import com.sparta.finalproject.auth.dto.AuthDto.KakaoUserInfoDto;
import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.common.jwt.JwtUtil;
import com.sparta.finalproject.common.redis.RedisUtil;
import com.sparta.finalproject.user.entity.User;
import com.sparta.finalproject.user.entity.UserRole;
import com.sparta.finalproject.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private static final String ADMIN_KEY = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    @Transactional
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

        User user = User.builder().userId(userId).password(password).email(email).role(role)
            .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void login(AuthDto.LoginDto loginDto, HttpServletResponse response) {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();

        User user = userRepository.findByUserId(userId).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String refreshToken = jwtUtil.createRefreshToken();
        user.updateRefreshToken(refreshToken);

        userRepository.saveAndFlush(user);
        redisUtil.setDataExpire(user.getUserId(), refreshToken, REFRESH_TOKEN_VALID_TIME);
        addTokenToHeader(response, user);

    }

    @Override
    @Transactional
    public void logout(AuthDto.TokenDto tokenDto) {

        String accessToken = tokenDto.getAccessToken().substring(7);
        if (!jwtUtil.validateToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 access token");
        }

        Claims claim = jwtUtil.getUserInfoFromToken(accessToken);
        String userId = claim.getSubject();
        redisUtil.deleteData(userId);

        redisUtil.setDataExpire("JWT:BLACK_LIST:" + accessToken, "TRUE", 30);
    }


    @Override
    @Transactional
    public void delete(AuthDto.DeleteRequestDto deleteRequestDto, User user) {
        user = userRepository.findByUserId(deleteRequestDto.getUserId()).orElseThrow(
            () -> new BadRequestException("해당하는 사용자가 없습니다")
        );

        user.getUserId();
        userRepository.delete(user);
    }

    @Transactional
    public void reIssue(AuthDto.TokenDto tokenDto, HttpServletResponse response) {
        if (!jwtUtil.validateTokenExceptExpiration(tokenDto.getRefreshToken())) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        User user = findUserByToken(tokenDto);

        if (!user.getRefreshToken().equals(tokenDto.getRefreshToken())) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        String refreshToken = jwtUtil.createRefreshToken();

        user.updateRefreshToken(refreshToken);
        userRepository.saveAndFlush(user);

        addTokenToHeader(response, user);
    }

    public void addTokenToHeader(HttpServletResponse response, User user) {
        response.addHeader(AUTHORIZATION_HEADER,
            jwtUtil.createToken(user.getUserId(), user.getRole()));
        response.addHeader(JwtUtil.REFRESH_HEADER, jwtUtil.createRefreshToken());
    }

    private User findUserByToken(AuthDto.TokenDto tokenDto) {
        Claims claims = jwtUtil.getUserInfoFromToken(tokenDto.getAccessToken().substring(7));
        String userId = claims.getSubject();
        return userRepository.findByUserId(userId).orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
    }

    public String kakaoLogin(String code, HttpServletResponse response)
        throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getToken(code);

        // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

        // 3. 필요시에 회원가입
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        // 4. JWT 토큰 반환
        String createToken = jwtUtil.createToken(kakaoUser.getUserId(), kakaoUser.getRole());
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);

        return createToken;
    }

    // 1. "인가 코드"로 "액세스 토큰" 요청
    private String getToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "b8054d0d502a7f824aa2ca329d139607");
        body.add("redirect_uri", "http://localhost:5500/login.html");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoUserInfoRequest,
            String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
            .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
            .get("email").asText();
        log.info("카카오 사용자 정보: " + id + ", " + nickname + ", " + email);
        return new KakaoUserInfoDto(id, nickname, email);
    }

    // 3. 필요시에 회원가입
    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfo.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
            .orElse(null);
        if (kakaoUser == null) {
            // 카카오 사용자 email 동일한 email 가진 회원이 있는지 확인
            String kakaoEmail = kakaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kakaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // 기존 회원정보에 카카오 Id 추가
                kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
            } else {
                // 신규 회원가입
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);

                // email: kakao email
                String email = kakaoUserInfo.getEmail();

                List<String> frontRandom = new ArrayList<>(Arrays.asList
                    ("똑똑한", "현명한", "바보", "배고픈", "총명한", "아픈"));
                List<String> backRandom = new ArrayList<>(Arrays.asList("익현", "태이", "준혁", "동현"));

                Random rd = new Random();

                String nickName = frontRandom.get(rd.nextInt(6)) + " "
                    + backRandom.get((rd.nextInt(4)));

                while (userRepository.existsByUserId(nickName)) {
                    nickName += "1";
                }

                kakaoUser = new User(nickName, kakaoId, encodedPassword, email,
                    UserRole.USER);
            }

            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }
}
