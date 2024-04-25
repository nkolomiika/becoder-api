package com.example.becoderapi.model.exceptions.transactions;

import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;

public class NegativeCostException extends TransactionRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! You should have positive cost in transaction";
    }
}
