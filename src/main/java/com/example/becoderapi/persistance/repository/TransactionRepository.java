package com.example.becoderapi.persistance.repository;

import com.example.becoderapi.model.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByIdTransaction(String id);

    @Modifying
    @Query("SELECT t FROM Transaction t WHERE t.buyerId = :id OR t.sellerId = :id")
    List<Transaction> findIdTransactions(@Param("id") String id);

    @Modifying
    @Query("UPDATE Account a SET a.balance = :sum WHERE a.id = :id")
    void updateBalance(@Param("id") String id, @Param("sum") double sum);
}
