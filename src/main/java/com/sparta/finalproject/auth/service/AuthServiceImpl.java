package com.sparta.finalproject.auth.service;

import static com.sparta.finalproject.common.jwt.JwtUtil.AUTHORIZATION_HEADER;
import static com.sparta.finalproject.common.jwt.JwtUtil.REFRESH_TOKEN_VALID_TIME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.finalproject.auth.dto.DeleteRequestDto;
import com.sparta.finalproject.auth.dto.KakaoUserInfoDto;
import com.sparta.finalproject.auth.dto.LoginDto;
import com.sparta.finalproject.auth.dto.SignupDto;
import com.sparta.finalproject.auth.dto.TokenDto;
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
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    JavaMailSender emailSender;
    public static final String ePw = createKey();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private static final String ADMIN_KEY = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    @Transactional
    public void signup(SignupDto signupDto) {
        String userId = signupDto.getUserId();
        String password = passwordEncoder.encode(signupDto.getPassword());

        //??????????????????
        Optional<User> found = userRepository.findByUserId(userId);

        if (found.isPresent()) {
            throw new IllegalArgumentException("????????? username ?????????.");
        }

        String email = signupDto.getEmail();

        //????????? Role ??????
        UserRole role = UserRole.USER;
        if (signupDto.isAdmin()) {
            if (!signupDto.getAdminKey().equals(ADMIN_KEY)) {
                throw new IllegalArgumentException("????????? ?????? ?????? ????????? ????????? ?????????.");
            }
            role = UserRole.ADMIN;
        }

        User user = User.builder().userId(userId).password(password).email(email).role(role)
            .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void login(LoginDto loginDto, HttpServletResponse response) {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();

        User user = userRepository.findByUserId(userId).orElseThrow(
            () -> new IllegalArgumentException("????????? ???????????? ????????????.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("??????????????? ???????????? ????????????.");
        }

        String refreshToken = jwtUtil.createRefreshToken();
        user.updateRefreshToken(refreshToken);

        userRepository.saveAndFlush(user);
        redisUtil.setDataExpire(user.getUserId(), refreshToken, REFRESH_TOKEN_VALID_TIME);
        addTokenToHeader(response, user, refreshToken);
    }

    @Override
    @Transactional
    public void logout(TokenDto tokenDto) {

        String accessToken = tokenDto.getAccessToken().substring(7);
        if (!jwtUtil.validateToken(accessToken)) {
            throw new IllegalArgumentException("???????????? ?????? access token");
        }

        Claims claim = jwtUtil.getUserInfoFromToken(accessToken);
        String userId = claim.getSubject();
        redisUtil.deleteData(userId);

        redisUtil.setDataExpire("JWT:BLACK_LIST:" + accessToken, "TRUE", 30);
    }


    @Override
    @Transactional
    public void delete(DeleteRequestDto deleteRequestDto, User user) {
        user = userRepository.findByUserId(deleteRequestDto.getUserId()).orElseThrow(
            () -> new BadRequestException("???????????? ???????????? ????????????")
        );

        userRepository.delete(user);
    }

    @Transactional
    public void reIssue(TokenDto tokenDto, HttpServletResponse response) {
        if (!jwtUtil.validateTokenExceptExpiration(tokenDto.getRefreshToken())) {
            throw new IllegalArgumentException("???????????? ?????? ???????????????.");
        }

        User user = findUserByToken(tokenDto);

        if (!user.getRefreshToken().equals(tokenDto.getRefreshToken())) {
            throw new IllegalArgumentException("???????????? ?????? ???????????????.");
        }

        String refreshToken = jwtUtil.createRefreshToken();

        user.updateRefreshToken(refreshToken);
        userRepository.saveAndFlush(user);

        addTokenToHeader(response, user, refreshToken);
    }

    @Transactional
    public void addTokenToHeader(HttpServletResponse response, User user, String refreshToken) {
        response.addHeader(AUTHORIZATION_HEADER,
            jwtUtil.createToken(user.getUserId(), user.getRole()));
        response.addHeader(JwtUtil.REFRESH_HEADER, refreshToken);
    }

    private User findUserByToken(TokenDto tokenDto) {
        Claims claims = jwtUtil.getUserInfoFromToken(tokenDto.getAccessToken().substring(7));
        String userId = claims.getSubject();
        return userRepository.findByUserId(userId).orElseThrow(
            () -> new IllegalArgumentException("???????????? ?????? ??????????????????.")
        );
    }

    private MimeMessage createMessage(String to) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);//????????? ??????
        message.setSubject("[GRASP] ??????????????? ?????????????????????.");//??????

        String msgg = "";
        msgg += "<div style='margin:20px;'>";
        msgg += "<h1> ??????????????? GRASP?????????. </h1>";
        msgg += "<br>";
        msgg += "<p>????????? ??????????????? GRASP??? ??????????????????.<p>";
        msgg += "<br>";
        msgg += "<p>??????????????? 5??? ??? ????????????, ????????? 5??? ?????? ?????? ????????? ????????????.<p>";
        msgg += "<br>";
        msgg += "<p>???????????????.<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:Sans-Serif';>";
        msgg += "<h3 style='color:blue;'>???????????? ?????? ???????????????.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//??????
        message.setFrom(new InternetAddress("sulsa1544@gmail.com", "GRASP"));//????????? ??????

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // ???????????? 8??????
            int index = rnd.nextInt(3); // 0~2 ?????? ??????

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public void sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);

        try {//????????????
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        // ?????? ??????(5???)?????? ??????
        redisUtil.setDataExpire("email: " + to, "code: " + ePw, 60 * 5L);

    }

    public String kakaoLogin(String code, HttpServletResponse response)
        throws JsonProcessingException {
        // 1. "?????? ??????"??? "????????? ??????" ??????
        String accessToken = getToken(code);

        // 2. ???????????? ????????? API ?????? : "????????? ??????"?????? "????????? ????????? ??????" ????????????
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

        // 3. ???????????? ????????????
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        // 4. JWT ?????? ??????
        String createToken = jwtUtil.createToken(kakaoUser.getUserId(), kakaoUser.getRole());
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);

        return createToken;
    }

    // 1. "?????? ??????"??? "????????? ??????" ??????
    private String getToken(String code) throws JsonProcessingException {
        // HTTP Header ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body ??????
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "b8054d0d502a7f824aa2ca329d139607");
        body.add("redirect_uri",
            "http://grasp-client.s3-website.ap-northeast-2.amazonaws.com/login.html");
        body.add("code", code);

        // HTTP ?????? ?????????
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        // HTTP ?????? (JSON) -> ????????? ?????? ??????
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    // 2. ???????????? ????????? API ?????? : "????????? ??????"?????? "????????? ????????? ??????" ????????????
    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP ?????? ?????????
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
        log.info("????????? ????????? ??????: " + id + ", " + nickname + ", " + email);
        return new KakaoUserInfoDto(id, nickname, email);
    }

    // 3. ???????????? ????????????
    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
        // DB ??? ????????? Kakao Id ??? ????????? ??????
        Long kakaoId = kakaoUserInfo.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
            .orElse(null);
        if (kakaoUser == null) {
            // ????????? ????????? email ????????? email ?????? ????????? ????????? ??????
            String kakaoEmail = kakaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kakaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // ?????? ??????????????? ????????? Id ??????
                kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
            } else {
                // ?????? ????????????
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);

                // email: kakao email
                String email = kakaoUserInfo.getEmail();

                List<String> frontRandom = new ArrayList<>(Arrays.asList
                    ("?????????", "?????????", "??????", "?????????", "?????????", "??????"));
                List<String> backRandom = new ArrayList<>(Arrays.asList("??????", "??????", "??????", "??????"));

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
