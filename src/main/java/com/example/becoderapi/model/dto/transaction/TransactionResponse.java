package com.example.becoderapi.model.dto.transaction;

import com.example.becoderapi.model.data.Transaction;

public record TransactionResponse(String message, Transaction transaction) {
}
