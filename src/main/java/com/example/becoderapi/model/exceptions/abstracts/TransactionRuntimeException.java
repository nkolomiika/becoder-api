package com.example.becoderapi.model.exceptions.abstracts;

public class TransactionRuntimeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Something happened wrong";
    }
}
