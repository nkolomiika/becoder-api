package com.example.becoderapi.persistance.services.repository;

import com.example.becoderapi.model.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getAccountById(String id);
    List<Account> getAllAccount();
    Optional<Account> getAccountByLogin(String login);
}
