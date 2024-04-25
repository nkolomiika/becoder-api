package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.abstracts.AuthRuntimeException;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionController {

    @ExceptionHandler(
            value = {TransactionRuntimeException.class, RuntimeException.class, AuthRuntimeException.class})
    public ResponseEntity<Response> handleTransactionRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new Response(
                        exception.getMessage()
                )
        );
    }

}
