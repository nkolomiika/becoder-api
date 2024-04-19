package com.example.becoderapi.persistance.services.repository;

import com.example.becoderapi.model.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> getTransactionByIdTransaction(String id);
}
