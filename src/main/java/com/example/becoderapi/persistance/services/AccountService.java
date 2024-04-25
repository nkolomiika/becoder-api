package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.dto.TransactionRequest;

public interface AccountService {
    Response info();

    Response getInfoById(Request request);

    Response createAccount(AuthRequest request);
}
