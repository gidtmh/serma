package com.example.server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String username;

    private String accessToken;

    private String refreshToken;

    private String role;
}