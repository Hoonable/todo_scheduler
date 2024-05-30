package com.sparta.project2.filter;

import com.sparta.project2.jwt.JwtUtil;
import com.sparta.project2.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@Order(2)
public class AuthFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);
    private final JwtUtil jwtUtil;

    public AuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        if (StringUtils.hasText(url) &&
                (url.startsWith("/todo")||url.startsWith("/auth"))
        ) {
            log.info("인증 처리 하지 않는 URL");
            chain.doFilter(request, response);
        } else {

            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);
            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
                }
                chain.doFilter(request, response);
            } else {

                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }

        }
    }
}
