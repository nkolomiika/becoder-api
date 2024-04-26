package com.example.becoderapi.controllers;


import com.example.becoderapi.model.dto.auth.AuthRequest;
import com.example.becoderapi.model.dto.auth.AuthResponse;
import com.example.becoderapi.persistance.services.AuthenticService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticController {

    private final AuthenticService authenticService;

    @Operation(summary = "Зарегистрироваться")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticService.register(request));
    }

    @Operation(summary = "Войти в аккаунт")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticService.login(request));
    }


}
