package com.example.becoderapi.persistance.services.repository;

import com.example.becoderapi.model.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> getTransactionByIdTransaction(String id);
    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance + :sum WHERE a.id = :id")
    void updateBalance(@Param("id") String id, @Param("sum") double sum);
}
