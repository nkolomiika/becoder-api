package com.example.becoderapi.model.exceptions.transactions;

import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;

public class NotEnoughMoneyException extends TransactionRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Not enough money";
    }
}
