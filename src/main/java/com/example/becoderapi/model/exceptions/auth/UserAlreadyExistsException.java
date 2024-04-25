package com.example.becoderapi.model.exceptions.auth;

import com.example.becoderapi.model.exceptions.abstracts.AuthRuntimeException;

public class UserAlreadyExistsException extends AuthRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! User with that login is already exists";
    }
}
