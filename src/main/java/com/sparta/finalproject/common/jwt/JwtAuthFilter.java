package com.sparta.finalproject.common.jwt;

import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        try {
            // 헤더에서 JWT 토큰을 받아옵니다.
            String token = jwtUtil.resolveToken((HttpServletRequest) request);
            // 유효한 토큰인지 확인합니다.
            if (token != null && jwtUtil.validateTokenExceptExpiration(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);
                // 토큰이 유효하면 토큰으로부터 유저 정보를 가져옵니다.
                Authentication authentication = jwtUtil.createAuthentication(info.getSubject());
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (UsernameNotFoundException exception) {
            HttpServletResponse realResponse = (HttpServletResponse) response;
            log.error("e = {}", exception.getMessage());
            realResponse.setContentType("application/json;charset=UTF-8");
            realResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            JSONObject responseJson = new JSONObject();
            responseJson.put("message", exception.getMessage());
            responseJson.put("code", 400);

            realResponse.getWriter().print(responseJson);
        }
    }
}
