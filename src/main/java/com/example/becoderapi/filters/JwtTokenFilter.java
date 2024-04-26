package com.example.becoderapi.filters;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer";
    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = jwtTokenUtil.extractTokenFromJwt(header);
        UserDetails userDetails;
        try {
            Account tmp = accountRepository
                    .findAccountById(jwtTokenUtil.getId(token))
                    .orElse(null);

            userDetails = tmp == null ? null : userDetailsService.loadUserByUsername(tmp.getId());
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ? List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    /*protected void doFilter(HttpServletRequest request,
                            @NonNull HttpServletResponse response,
                            @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            String token = jwtTokenUtil.extractTokenFromHeader(request);
            String extractedId = jwtTokenUtil.getId(token);

            BufferedReader reader = request.getReader();
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            String id = new ObjectMapper().readValue(body.toString(), Account.class).getId();

            if (!extractedId.equals(id))
                throw new AccessDeniedException("ID from JWT does not match ID from request body");

        }
        filterChain.doFilter(request, response);

    }*/

}
