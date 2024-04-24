package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.data.Transaction;
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
        return new TransactionResponse("Success transaction!", contract(request));
    }

    private Transaction contract(Request request)
            throws NoSuchAccountException, NegativeCostException, NotEnoughMoneyException {

        Account buyer = accountRepository.getAccountById(request.buyerId())
                .orElseThrow(() -> {
                    throw new NoSuchAccountException();
                });

        Account seller = accountRepository.getAccountById(request.sellerId())
                .orElseThrow(() -> {
                    throw new NoSuchAccountException();
                });

        double buyerBalance = buyer.getBalance();
        double sellerBalance = seller.getBalance();
        double contractSum = request.sum();

        if (contractSum < 0) throw new NegativeCostException();
        if (buyerBalance - contractSum < 0) throw new NotEnoughMoneyException();

        buyer.setBalance(buyerBalance - request.sum());
        seller.setBalance(sellerBalance + request.sum());
        return transactionRepository.save(new Transaction(
                request.buyerId(), request.sellerId(), request.sum()
        ));
    }
}
