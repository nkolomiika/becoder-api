package com.example.becoderapi.utils;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.security.JwtConfiguration;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtConfiguration.class)
public class JwtTokenUtil {

    private final JwtConfiguration jwtConfiguration;

    public String createToken(Account account) {

        Claims claims = Jwts.claims().setSubject(account.getLogin());
        claims.put("id", String.valueOf(account.getId()));

        Date tokenValidity = new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(jwtConfiguration.exp()));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, jwtConfiguration.secret())
                .compact();
    }

    public String getId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfiguration.secret())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", String.class);
    }


    public String extractTokenFromJwt(String jwt) {
        return jwt.split(" ")[1].trim();
    }
}
