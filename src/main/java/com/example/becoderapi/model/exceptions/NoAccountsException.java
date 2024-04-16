package com.example.becoderapi.model.exceptions;

public class NoAccountsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! No accounts yet";
    }
}
