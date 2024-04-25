package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;

public interface AccountService {
    Response info();

    Response getInfoById(Request request);

    Response createAccount(AuthRequest request);

    Response login(AuthRequest request);

    Account checkLoginAndPassword(AuthRequest request);
}
