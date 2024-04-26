package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.data.Transaction;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.dto.transaction.TransactionRequest;
import com.example.becoderapi.model.dto.transaction.TransactionResponse;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.transactions.NegativeCostException;
import com.example.becoderapi.model.exceptions.transactions.NotEnoughMoneyException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.repository.TransactionRepository;
import com.example.becoderapi.persistance.services.TransactionService;
import com.example.becoderapi.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional(rollbackFor = {TransactionRuntimeException.class})
    public TransactionResponse makeContract(
            TransactionRequest request) throws TransactionRuntimeException, JwtException {

        return new TransactionResponse("Success transaction!", contract(request));
    }

    private Transaction contract(TransactionRequest request)
            throws NoSuchAccountException, NegativeCostException, NotEnoughMoneyException {

        Account buyer = accountRepository.findAccountById(request.buyerId())
                .orElseThrow(NoSuchAccountException::new);

        Account seller = accountRepository.findAccountById(request.sellerId())
                .orElseThrow(NoSuchAccountException::new);

        double buyerBalance = buyer.getBalance();
        double sellerBalance = seller.getBalance();
        double contractSum = request.sum();

        if (contractSum < 0) throw new NegativeCostException();
        if (buyerBalance - contractSum < 0) throw new NotEnoughMoneyException();

        buyer.setBalance(buyerBalance - contractSum);
        seller.setBalance(sellerBalance + contractSum);

        transactionRepository.updateBalance(buyer.getId(), -1 * contractSum);
        transactionRepository.updateBalance(seller.getId(), contractSum);


        return transactionRepository.save(new Transaction(
                request.buyerId(), request.sellerId(), contractSum
        ));
    }

    @Override
    @Transactional(rollbackFor = {TransactionRuntimeException.class})
    public Response updateBalance(TransactionRequest request) throws TransactionRuntimeException {
        Account account = accountRepository.findAccountById(request.sellerId()).orElseThrow(
                () -> {
                    throw new NoSuchAccountException();
                });

        double contractSum = request.sum();
        if (contractSum < 0) throw new NegativeCostException();

        transactionRepository.updateBalance(account.getId(), contractSum);

        return new Response(
                String.format("Balance successfully updated with %s", contractSum)
        );
    }

}
