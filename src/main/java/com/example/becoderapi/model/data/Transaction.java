package com.example.becoderapi.model.data;

import com.example.becoderapi.model.utils.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;

@Getter
@Entity
@Table
@NoArgsConstructor
public class Transaction {

    @Id
    @Column
    private String idTransaction;
    @Column
    private String idHolder;
    @Column
    private Type type;
    @Column
    private double cost;
    @Column
    private Time timeInit;

    //todo придумать как подвязать завершенность сделки в параметр
    //private final boolean isComplete;

    public Transaction(String id, Type type, double cost) {
        this.idTransaction = IdGenerator.generate();
        this.idHolder = id;
        this.type = type;
        this.cost = cost;
        this.timeInit = Time.valueOf(LocalTime.now());
    }

    public String toJSON() {
        return "";
    }
}
