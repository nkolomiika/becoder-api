package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;

public interface AccountService {
    Response info();

    Account getInfoById(Request request);

    Response getTransactions(String token);

}
