package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.dto.TransactionRequest;
import com.example.becoderapi.model.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse makeContract(TransactionRequest request);

    Response updateBalance(TransactionRequest request);

}