package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
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
    public Response createAccount() {
        return new Response(
                accountRepository.save(new Account()).toString()
        );
    }
}
