package com.example.server.exception;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(e.getMessage());
        }
    }
}
