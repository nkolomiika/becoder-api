package com.example.becoderapi.controllers;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.persistance.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
@Tag(name = "Авторизация")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Получить информацию обо всех аккаунтов")
    @GetMapping("/info")
    public ResponseEntity<Response> info() {
        return ResponseEntity.ok(accountService.info());
    }

    @Operation(summary = "Получить информацию об аккаунте по айди")
    @PostMapping("/info-id")
    public ResponseEntity<Account> info(@RequestBody Request request) {
        return ResponseEntity.ok(accountService.getInfoById(request));
    }

    @Operation(summary = "Получить информацию по транзакциям пользователя")
    @GetMapping("/id-transactions")
    public ResponseEntity<Response> getTransactionsById(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(accountService.getTransactions(token));
    }
}
