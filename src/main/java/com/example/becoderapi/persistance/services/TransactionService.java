package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.dto.transaction.TransactionRequest;
import com.example.becoderapi.model.dto.transaction.TransactionResponse;

public interface TransactionService {
    TransactionResponse makeContract(String jwt, TransactionRequest request);

    Response updateBalance(TransactionRequest request);

}