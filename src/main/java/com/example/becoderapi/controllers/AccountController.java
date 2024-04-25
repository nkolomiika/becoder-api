package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.persistance.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Авторизация")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Получить информацию обо всех аккаунтов")
    @GetMapping("/info")
    public ResponseEntity<Response> info() {
        return ResponseEntity.ok(accountService.info());
    }

    @Operation(summary = "Получить информацию об аккаунте по айди")
    @GetMapping("/info-id")
    public ResponseEntity<Response> info(@RequestBody Request request) {
        return ResponseEntity.ok(accountService.getInfoById(request));
    }

    @Operation(summary = "Зарегистрироваться")
    @PostMapping("/register")
    public ResponseEntity<Response> createAccount(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @Operation(summary = "Войти в аккаунт")
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(accountService.login(request));
    }

    @PostMapping("/test")
    public ResponseEntity<Response> test(@RequestBody AuthRequest request){
        accountService.createAccount(request);
        return ResponseEntity.ok(new Response(String.format("%s + %s", request.login(), request.password())));
    }
}
