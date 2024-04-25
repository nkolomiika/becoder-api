package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.abstracts.AuthRuntimeException;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionController {

    @ExceptionHandler(
            value = {TransactionRuntimeException.class, RuntimeException.class, AuthRuntimeException.class, ExpiredJwtException.class,
                    UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class})
    public ResponseEntity<Response> handleTransactionRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new Response(
                        exception.getMessage()
                )
        );
    }

}
