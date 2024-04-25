package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.dto.TransactionRequest;
import com.example.becoderapi.model.dto.TransactionResponse;
import com.example.becoderapi.persistance.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/contract")
    public ResponseEntity<TransactionResponse> contract(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.makeContract(request));
    }

    @PostMapping("/update-balance")
    public ResponseEntity<Response> updateBalance(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.updateBalance(request));
    }

}
