package com.example.becoderapi.model.data;

import com.example.becoderapi.utils.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;

@Getter
@Entity
@Table
@NoArgsConstructor
public class Transaction {

    @Id
    @Column
    private String idTransaction;
    @Column
    private String buyerId;
    @Column
    private String sellerId;
    @Column
    private double cost;
    @Column
    @CreationTimestamp
    private Time timeInit;

    public Transaction(String buyerId, String sellerId, double cost) {
        this.idTransaction = IdGenerator.generate();
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.cost = cost;
    }

    public String toJSON() {
        return "";
    }
}
