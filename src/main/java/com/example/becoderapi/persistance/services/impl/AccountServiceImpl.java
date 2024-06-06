package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AccountResponse;
import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.repository.TransactionRepository;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.utils.JwtTokenUtil;
import com.example.becoderapi.utils.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public AccountResponse getInfoById(Request request) throws NoSuchAccountException {
        Account account = accountRepository.findAccountById(request.id())
                .orElseThrow(NoSuchAccountException::new);
        return new AccountResponse(account.getId(), account.getBalance());
    }

    @Override
    public Response info() {
        return new Response(
                accountRepository.findAll()
                        .stream()
                        .map(ObjectMapper::toAccountDto)
                        .toList());
    }

    @Override
    public Response getTransactions(String jwt) {
        String token = jwtTokenUtil.extractTokenFromJwt(jwt);
        String id = jwtTokenUtil.getId(token);
        return new Response(transactionRepository.findIdTransactions(id).stream().toList());
    }
}
