package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.data.Transaction;
import com.example.becoderapi.model.data.Type;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.TransactionResponse;
import com.example.becoderapi.model.exceptions.NegativeCostException;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.NotEnoughMoneyException;
import com.example.becoderapi.model.exceptions.TransactionRuntimeException;
import com.example.becoderapi.persistance.services.TransactionService;
import com.example.becoderapi.persistance.services.repository.AccountRepository;
import com.example.becoderapi.persistance.services.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponse makeContract(Request request) throws TransactionRuntimeException {
        Transaction transaction = request.type() == Type.BUY ? buy(request) : sell(request);
        return new TransactionResponse("Success transaction!", transaction);
    }

    private Transaction buy(Request request) throws NoSuchAccountException, NegativeCostException {
        Account admin = accountRepository.getAccountById(request.id())
                .orElseThrow(() -> {
                    throw new NoSuchAccountException();
                });

        var aBalance = admin.getBalance();

        if (request.cost() < 0) throw new NegativeCostException();

        admin.setBalance(aBalance + request.cost());
        return transactionRepository.save(new Transaction(request.id(), request.type(), request.cost()));
    }

    private Transaction sell(Request request)
            throws NoSuchAccountException, NotEnoughMoneyException, NegativeCostException {
        var admin = accountRepository.getAccountById(request.id())
                .orElseThrow(() -> {
                    throw new NoSuchAccountException();
                });
        var aBalance = admin.getBalance();

        if (request.cost() < 0) throw new NegativeCostException();
        if (aBalance - request.cost() < 0) throw new NotEnoughMoneyException();

        admin.setBalance(aBalance - request.cost());
        return transactionRepository.save(new Transaction(request.id(), request.type(), request.cost()));
    }
}
