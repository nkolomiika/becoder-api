package com.example.becoderapi.controllers;

import com.example.becoderapi.model.dto.basic.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionController {

    @ExceptionHandler(
            value = {Exception.class})
    public ResponseEntity<Response> handleTransactionRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new Response(exception.getMessage())
        );
    }

}
