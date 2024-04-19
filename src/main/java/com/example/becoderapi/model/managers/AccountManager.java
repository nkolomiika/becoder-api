package com.example.becoderapi.model.managers;

import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AccountManager {
    private final HashMap<String, Account> accounts = new HashMap<>();

    public Account getAccountById(String id) throws NoSuchAccountException {
        var acc = accounts.get(id);
        if (acc == null) throw new NoSuchAccountException();
        return acc;
    }

    public String createAccount() {
        var acc = new Account();
        accounts.put(acc.getId(), acc);
        return acc.getId();
    }

    public String getAllAccounts() {
        var sb = new StringBuilder();
        this.accounts.forEach((id, acc) ->
                sb.append(acc).append("\n")
        );
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


}
