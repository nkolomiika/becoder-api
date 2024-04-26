package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.dto.transaction.TransactionRequest;
import com.example.becoderapi.model.dto.transaction.TransactionResponse;
import com.example.becoderapi.persistance.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Tag(name = "Изменение баланса и заключение контрактов")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Заключить контракт")
    @PostMapping("/contract")
    public ResponseEntity<TransactionResponse> contract(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.makeContract(request));
    }

    @Operation(summary = "Обновить баланс")
    @PostMapping("/update-balance")
    public ResponseEntity<Response> updateBalance(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.updateBalance(request));
    }

}
