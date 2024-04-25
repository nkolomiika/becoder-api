package com.example.becoderapi.model.exceptions.transactions;

import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;

public class NoSuchAccountException extends TransactionRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Cannot find account with that id";
    }
}
