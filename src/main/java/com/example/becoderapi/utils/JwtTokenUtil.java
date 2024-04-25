package com.example.becoderapi.utils;

import com.example.becoderapi.security.JwtConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(JwtConfiguration.class)
public class JwtTokenUtil {
    public boolean validate(String token) {
        return true;
    }

    public String getId(String token) {
        return "";
    }
}
