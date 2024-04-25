package com.example.becoderapi.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfiguration(
        @NotBlank String secret,
        long exp
) {
}
