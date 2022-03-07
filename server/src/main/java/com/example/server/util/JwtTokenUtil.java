package com.example.server.util;

import com.example.server.configurationprops.ConfigJwtProperties;
import com.example.server.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final ConfigJwtProperties configJwtProperties;
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser()
            .setSigningKey(configJwtProperties.getAccessTokenSecret())
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token, boolean isRefreshToken) {
        try {
            Jws<Claims> claims =
                Jwts.parser()
                    .setSigningKey(
                        isRefreshToken
                            ? configJwtProperties.getRefreshTokenSecret()
                            : configJwtProperties.getAccessTokenSecret())
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
}
