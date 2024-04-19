package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse makeContract(Request request);
}