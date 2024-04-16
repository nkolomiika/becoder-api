package com.example.becoderapi.model.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Not enough money";
    }
}
