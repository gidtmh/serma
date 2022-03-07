package com.example.server.controller;

import com.example.server.auth.JwtUser;
import com.example.server.dto.request.AuthRequest;
import com.example.server.dto.request.RefreshTokensRequest;
import com.example.server.dto.response.AuthResponse;
import com.example.server.dto.response.RefreshTokensResponse;
import com.example.server.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {

        AuthResponse responseDto = authService.authenticate(authRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/refresh-tokens")
    public ResponseEntity<RefreshTokensResponse> refreshTokens(
        @AuthenticationPrincipal JwtUser user,
        @Valid @RequestBody RefreshTokensRequest refreshTokensRequest) {
        RefreshTokensResponse refreshTokensResponse = authService.refreshTokens(user,
            refreshTokensRequest);
        return new ResponseEntity<>(refreshTokensResponse, HttpStatus.OK);
    }
}
