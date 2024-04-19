package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.TransactionRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionController {

    @ExceptionHandler(value = {TransactionRuntimeException.class})
    public ResponseEntity<Response> handleNoAccount(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new Response(
                        exception.getMessage()
                )
        );
    }

}
