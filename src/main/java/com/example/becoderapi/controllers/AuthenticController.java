package com.example.becoderapi.controllers;


import com.example.becoderapi.model.dto.AccountResponse;
import com.example.becoderapi.model.dto.auth.AuthRequest;
import com.example.becoderapi.model.dto.auth.AuthResponse;
import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.persistance.services.AuthenticService;
import com.example.becoderapi.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticController {

    private final AuthenticService authenticService;
    private final AccountService accountService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "Зарегистрироваться")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticService.register(request));
    }

    @GetMapping("/check")
    public ResponseEntity<AccountResponse> check(@RequestHeader("Authorization") String jwt) {
        if (!jwt.isBlank()) {
            String id = jwtTokenUtil.getId(jwtTokenUtil.extractTokenFromJwt(jwt));
            if (id == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return ResponseEntity.ok(accountService.getInfoById(new Request(id)));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Operation(summary = "Войти в аккаунт")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticService.login(request));
    }
}
