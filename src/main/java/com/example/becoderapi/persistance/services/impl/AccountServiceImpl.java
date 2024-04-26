package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.dto.basic.Request;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.services.AccountService;
import com.example.becoderapi.utils.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public Response getInfoById(Request request) throws NoSuchAccountException {
        return new Response(
                accountRepository.findAccountById(request.id())
                        .orElseThrow(NoSuchAccountException::new)
                        .toString()
        );
    }

    @Override
    public Response info() {
        return new Response(
                accountRepository.findAll()
                        .stream()
                        .map(ObjectMapper::toAccountDto)
                        .toList());
    }

}