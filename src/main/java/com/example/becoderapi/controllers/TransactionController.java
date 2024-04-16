package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy/{id}")
    public ResponseEntity<Response> buy(@PathVariable("id") String id, Request request) {
        return ResponseEntity.ok(transactionService.buy(request));
    }

    @PostMapping("/sell/{id}")
    public ResponseEntity<Response> sell(@PathVariable("id") String id, Request request) {
        return ResponseEntity.ok(transactionService.sell(request));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Response> info(@PathVariable("id") String id) {
        return ResponseEntity.ok(transactionService.info());
    }

}
