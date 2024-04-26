package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.data.Transaction;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.dto.transaction.TransactionRequest;
import com.example.becoderapi.model.dto.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse makeContract(TransactionRequest request);

    Response updateBalance(TransactionRequest request);

}