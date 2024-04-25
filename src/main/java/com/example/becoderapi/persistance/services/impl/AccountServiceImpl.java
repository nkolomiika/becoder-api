package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.auth.UserAlreadyExistsException;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.persistance.repository.AccountRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public Response getInfoById(Request request) throws NoSuchAccountException {
        return new Response(
                accountRepository.findAccountById(request.id())
                        .orElseThrow(() -> {
                            throw new NoSuchAccountException();
                        })
                        .toString()
        );
    }

    @Override
    public Response info() {
        return new Response(
                accountRepository.findAll()
                        .stream()
                        .map(acc -> acc.toString() + "\n")
                        .toString()
        );
    }

    @Override
    public Response createAccount(AuthRequest request) throws UserAlreadyExistsException {

        if (accountRepository.findAccountByLogin(request.login()).isPresent())
            throw new UserAlreadyExistsException();

        return new Response(
                accountRepository.save(new Account(request.login(), request.password())).toString()
        );
    }
}
