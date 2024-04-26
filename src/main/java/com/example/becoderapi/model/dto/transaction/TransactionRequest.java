package com.example.becoderapi.model.dto.transaction;

public record TransactionRequest(String buyerId, String sellerId, double sum) {
}
