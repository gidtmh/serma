package com.example.server.auth;

import com.example.server.configurationprops.ConfigJwtProperties;
import com.example.server.model.Employee;
import com.example.server.repository.EmployeeRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final ConfigJwtProperties configJwtProperties;
    private final EmployeeRepository employeeRepository;

    public String generateAccessToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validityDate = new Date(now.getTime() + configJwtProperties.getAccessTokenExpired());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validityDate)
            .signWith(SignatureAlgorithm.HS512, configJwtProperties.getAccessTokenSecret())
            .compact();
    }

    public String generateRefreshToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date validity = new Date(now.getTime() + configJwtProperties.getRefreshTokenExpired());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS512, configJwtProperties.getRefreshTokenSecret())
            .compact();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return username -> {
            Employee employee =
                employeeRepository
                    .findByName(username)
                    .orElseThrow(
                        () -> new UsernameNotFoundException(
                            "User with username: " + username + " not found"));

            return JwtUserFactory.create(employee);
        };
    }
}
