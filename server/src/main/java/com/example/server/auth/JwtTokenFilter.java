package com.example.server.auth;

import com.example.server.util.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {

        String token = jwtTokenUtil.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenUtil.validateToken(token, false)) {
            Authentication auth = jwtTokenUtil.getAuthentication(token);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}
