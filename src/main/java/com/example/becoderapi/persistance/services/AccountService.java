package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;

public interface AccountService {
    Response info();
    Response getInfoById(Request request);
    Response createAccount();
}
