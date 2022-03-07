package com.example.server.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokensResponse {

    private String accessToken;

    private String refreshToken;
}
