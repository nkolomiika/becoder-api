package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.persistance.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/info")
    public ResponseEntity<Response> info() {
        return ResponseEntity.ok(accountService.info());
    }

    @GetMapping("/info-id")
    public ResponseEntity<Response> info(@RequestBody Request request) {
        return ResponseEntity.ok(accountService.getInfoById(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> createAccount(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(accountService.login(request));
    }
}
