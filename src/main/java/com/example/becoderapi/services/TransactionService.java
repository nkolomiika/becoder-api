package com.example.becoderapi.services;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.NegativeCostException;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.NotEnoughMoneyException;
import com.example.becoderapi.model.managers.AccountManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private AccountManager manager;

    public Response buy(Request request) throws NoSuchAccountException, NegativeCostException {
        var admin = manager.getAccountById(request.id());
        var aBalance = admin.getBalance();

        if (request.cost() < 0) throw new NegativeCostException();

        admin.setBalance(aBalance + request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", aBalance, admin.getId()
        ));
    }

    public Response sell(Request request)
            throws NoSuchAccountException, NotEnoughMoneyException, NegativeCostException {
        var admin = manager.getAccountById(request.id());
        var aBalance = admin.getBalance();

        if (request.cost() < 0) throw new NegativeCostException();
        if (aBalance - request.cost() < 0) throw new NotEnoughMoneyException();

        admin.setBalance(aBalance - request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", aBalance, admin.getId()
        ));
    }

    public Response getInfoById(Request request) throws NoSuchAccountException {
        return new Response(manager.getAccountById(request.id()).toString());
    }

    public Response info() {
        return new Response(
                manager.getAllAccounts()
        );
    }

    public Response createAccount() {
        return new Response(
                manager.createAccount()
        );
    }
}
