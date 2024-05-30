package com.sparta.project2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            String url = httpServletRequest.getRequestURI();

            chain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpResponse.setStatus(400);
            String jsonErrorMessage = String.format("{\"error\": \"%s\"}", e.getMessage());
            httpResponse.setCharacterEncoding("UTF-8"); // 한글 출력 가능하도록
            httpResponse.getWriter().write(jsonErrorMessage);

        }
    }
}
