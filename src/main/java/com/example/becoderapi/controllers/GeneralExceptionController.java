package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.exceptions.abstracts.AuthRuntimeException;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionController {

    @ExceptionHandler(
            value = {
                    TransactionRuntimeException.class, JwtException.class,
                    AuthRuntimeException.class
            })
    public ResponseEntity<Response> handleTransactionRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new Response(exception.getMessage())
        );
    }

    @ExceptionHandler(
            value = {
                    Exception.class
            })
    public ResponseEntity<Response> testExceptionHandler(Exception exception) {
        return ResponseEntity.badRequest().body(
                new Response(exception.getStackTrace())
        );
    }

}
