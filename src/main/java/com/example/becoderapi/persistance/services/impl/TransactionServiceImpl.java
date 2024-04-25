package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.data.Transaction;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.dto.TransactionRequest;
import com.example.becoderapi.model.dto.TransactionResponse;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import com.example.becoderapi.model.exceptions.transactions.NegativeCostException;
import com.example.becoderapi.model.exceptions.transactions.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.transactions.NotEnoughMoneyException;
import com.example.becoderapi.persistance.services.TransactionService;
import com.example.becoderapi.persistance.services.repository.AccountRepository;
import com.example.becoderapi.persistance.services.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackFor = {TransactionRuntimeException.class})
    public TransactionResponse makeContract(TransactionRequest request) throws TransactionRuntimeException {
        return new TransactionResponse("Success transaction!", contract(request));
    }

    private Transaction contract(TransactionRequest request)
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

        buyer.setBalance(buyerBalance - contractSum);
        seller.setBalance(sellerBalance + contractSum);
        return transactionRepository.save(new Transaction(
                request.buyerId(), request.sellerId(), contractSum
        ));
    }

    @Override
    @Transactional(rollbackFor = {TransactionRuntimeException.class})
    public Response updateBalance(TransactionRequest request) throws TransactionRuntimeException {
        Account account = accountRepository.getAccountById(request.sellerId()).orElseThrow(
                () -> {
                    throw new NoSuchAccountException();
                }
        );

        double contractSum = request.sum();
        if (contractSum < 0) throw new NegativeCostException();
        transactionRepository.updateBalance(account.getId(), contractSum);

        return new Response(
                String.format("Balance successfully updated with %s", contractSum)
        );
    }

}
