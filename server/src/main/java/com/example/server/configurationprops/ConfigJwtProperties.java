package com.example.server.configurationprops;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class ConfigJwtProperties {

    private String accessTokenSecret;
    private String refreshTokenSecret;
    private long accessTokenExpired;
    private long refreshTokenExpired;
}
