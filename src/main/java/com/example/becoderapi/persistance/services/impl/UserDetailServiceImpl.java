package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws NoSuchAccountException {
        Account account = accountRepository.findAccountById(id).orElseThrow(
                () -> {
                    throw new NoSuchAccountException();
                }
        );
        return User.builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .build();
    }
}
