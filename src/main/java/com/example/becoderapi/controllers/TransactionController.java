package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
import com.example.becoderapi.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy")
    public ResponseEntity<Response> buy(@RequestBody Request request) {
        return ResponseEntity.ok(transactionService.buy(request));
    }

    @PostMapping("/sell")
    public ResponseEntity<Response> sell(@RequestBody Request request) {
        return ResponseEntity.ok(transactionService.sell(request));
    }

    @GetMapping("/info")
    public ResponseEntity<Response> info() {
        return ResponseEntity.ok(transactionService.info());
    }

    @GetMapping("/create")
    public ResponseEntity<Response> createAccount() {
        return ResponseEntity.ok(transactionService.createAccount());
    }

    @ExceptionHandler(value = {NoSuchAccountException.class})
    public ResponseEntity<Response> handleNoAccount(NoSuchAccountException exception) {
        return ResponseEntity.badRequest().body(
                new Response(
                        exception.getMessage()
                )
        );
    }

}
