package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.dto.TransactionRequest;
import com.example.becoderapi.model.exceptions.auth.UserAlreadyExistsException;
import com.example.becoderapi.model.exceptions.transactions.NegativeCostException;
import com.example.becoderapi.model.exceptions.transactions.NoSuchAccountException;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.persistance.services.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Response getInfoById(Request request) throws NoSuchAccountException {
        return new Response(
                accountRepository.getAccountById(request.id())
                        .orElseThrow(() -> {
                            throw new NoSuchAccountException();
                        })
                        .toString()
        );
    }

    @Override
    public Response info() {
        return new Response(
                accountRepository.getAllAccount()
                        .stream()
                        .map(acc -> acc.toString() + "\n")
                        .toString()
        );
    }

    @Override
    public Response createAccount(AuthRequest request) throws UserAlreadyExistsException {

        if (accountRepository.getAccountByLogin(request.login()).isPresent())
            throw new UserAlreadyExistsException();

        return new Response(
                accountRepository.save(new Account(request.login(), request.password())).toString()
        );
    }
}
