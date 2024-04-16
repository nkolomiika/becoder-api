package com.example.becoderapi.services;

import com.example.becoderapi.model.Account;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private Account admin;

    public Response buy(Request request) {
        admin.setBalance(admin.getBalance() + request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", admin.getBalance(), admin.getId()
        ));
    }

    public Response sell(Request request) {
        admin.setBalance(admin.getBalance() - request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", admin.getBalance(), admin.getId()
        ));
    }

    public Response info() {
        return new Response(String.format(
                "ID : %s\nBalance : %s", admin.getId(), admin.getBalance()
        ));
    }
}
