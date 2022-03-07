package com.example.server.service;

import com.example.server.auth.JwtTokenProvider;
import com.example.server.auth.JwtUser;
import com.example.server.dto.request.AuthRequest;
import com.example.server.dto.request.RefreshTokensRequest;
import com.example.server.dto.response.AuthResponse;
import com.example.server.dto.response.RefreshTokensResponse;
import com.example.server.exception.JwtAuthenticationException;
import com.example.server.model.Employee;
import com.example.server.repository.EmployeeRepository;
import com.example.server.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Employee employee =
            employeeRepository
                .findByName(authRequest.getUsername())
                .orElseThrow(
                    () -> new AuthenticationException("Username or password is incorrect") {
                    });

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getName(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new JwtAuthenticationException("Username or password is incorrect");
        }


        String accessToken = jwtTokenProvider.generateAccessToken(employee.getName());
        String refreshToken = jwtTokenProvider.generateRefreshToken(employee.getName());

        return AuthResponse.builder()
            .username(employee.getName())
            .role(employee.getRole().getRole().name())
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    public RefreshTokensResponse refreshTokens(JwtUser user,
        RefreshTokensRequest refreshTokensRequest) {
        String token = refreshTokensRequest.getRefreshToken();

        if (!jwtTokenUtil.validateToken(token, true)) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        return RefreshTokensResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
