package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.AccountResponse;
import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;

public interface AccountService {
    Response info();

    AccountResponse getInfoById(Request request);

    Response getTransactions(String token);

}
