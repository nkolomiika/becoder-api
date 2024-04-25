package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AuthRequest;
import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.auth.UserAlreadyExistsException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCrypt;


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

        Account account = Account.builder()
                .login(request.login())
                .password(request.password())
                .build();

        String token = jwtTokenUtil.createToken(account);
        accountRepository.save(account);

        return new Response(token);
    }

    @Override
    public Account checkLoginAndPassword(AuthRequest request) throws NoSuchAccountException {
        Account account = accountRepository.findAccountByLogin(request.login())
                .orElseThrow(() -> {throw new NoSuchAccountException();});
        if (bCrypt.matches(request.password(), account.getPassword())) {
            return account;
        }
        throw new NoSuchAccountException();
    }

    @Override
    public Response login(AuthRequest request) throws NoSuchAccountException {
        Account account = checkLoginAndPassword(request);
        String token = jwtTokenUtil.createToken(account);
        return new Response(token);
    }
}
