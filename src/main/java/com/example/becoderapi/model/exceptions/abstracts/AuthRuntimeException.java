package com.example.becoderapi.model.exceptions.abstracts;

public class AuthRuntimeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Something went wrong while authentication";
    }
}
