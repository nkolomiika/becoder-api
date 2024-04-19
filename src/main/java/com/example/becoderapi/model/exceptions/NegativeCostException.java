package com.example.becoderapi.model.exceptions;

public class NegativeCostException extends TransactionRuntimeException {
    @Override
    public String getMessage() {
        return "Oops! You should have positive cost in transaction";
    }
}
