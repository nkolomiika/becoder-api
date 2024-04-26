package com.example.becoderapi.utils;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.AccountResponse;

public class ObjectMapper {
    public static AccountResponse toAccountDto(Account account) {
        return new AccountResponse(account.getId(), account.getBalance());
    }

}
