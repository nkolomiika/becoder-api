package com.example.becoderapi.model.dto;

public record TransactionRequest(String buyerId, String sellerId, double sum) {
}
