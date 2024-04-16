package com.example.becoderapi.model.exceptions;

public class NoSuchAccountException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oops! Cannot find account with that id";
    }
}
