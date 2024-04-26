package com.example.becoderapi.persistance.services.impl;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.auth.AuthRequest;
import com.example.becoderapi.model.dto.auth.AuthResponse;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.auth.UserAlreadyExistsException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.services.AuthenticService;
import com.example.becoderapi.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticServiceImpl implements AuthenticService {

    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final BCryptPasswordEncoder bCrypt;

    @Override
    public AuthResponse register(AuthRequest request) {
        if (accountRepository.findAccountByLogin(request.login()).isPresent())
            throw new UserAlreadyExistsException();

        Account account = new Account(request.login(), bCrypt.encode(request.password()));

        String token = jwtTokenUtil.createToken(account);
        accountRepository.save(account);

        return new AuthResponse(token);
    }

    private Account checkLoginAndPassword(AuthRequest request) throws NoSuchAccountException {
        Account account = accountRepository.findAccountByLogin(request.login())
                .orElseThrow(NoSuchAccountException::new);
        if (bCrypt.matches(request.password(), account.getPassword())) {
            return account;
        }
        throw new NoSuchAccountException();
    }

    @Override
    public AuthResponse login(AuthRequest request) throws NoSuchAccountException {
        Account account = checkLoginAndPassword(request);
        String token = jwtTokenUtil.createToken(account);
        return new AuthResponse(token);
    }
}
