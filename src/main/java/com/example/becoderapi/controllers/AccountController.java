package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.persistance.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/info")
    public ResponseEntity<Response> info() {
        return ResponseEntity.ok(accountService.info());
    }

    @GetMapping("/infoID")
    public ResponseEntity<Response> info(@RequestBody Request request) {
        return ResponseEntity.ok(accountService.getInfoById(request));
    }

    @GetMapping("/createAccount")
    public ResponseEntity<Response> createAccount() {
        return ResponseEntity.ok(accountService.createAccount());
    }
}
