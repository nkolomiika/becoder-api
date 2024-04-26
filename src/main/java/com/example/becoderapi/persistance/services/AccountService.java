package com.example.becoderapi.persistance.services;

import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;

public interface AccountService {
    Response info();

    Response getInfoById(Request request);
}
