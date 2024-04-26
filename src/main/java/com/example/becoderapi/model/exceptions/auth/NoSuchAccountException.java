package com.example.becoderapi.model.exceptions.auth;

import com.example.becoderapi.model.exceptions.abstracts.AuthRuntimeException;

public class NoSuchAccountException extends AuthRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Cannot find account with that id";
    }
}
