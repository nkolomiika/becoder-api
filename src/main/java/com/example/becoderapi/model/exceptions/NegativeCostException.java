package com.example.becoderapi.model.exceptions;

public class NegativeCostException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! You should have positive cost in transaction";
    }
}
