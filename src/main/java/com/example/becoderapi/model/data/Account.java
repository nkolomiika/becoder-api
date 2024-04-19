package com.example.becoderapi.model.data;

import com.example.becoderapi.model.utils.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Entity
@Table
public class Account {

    @Id
    @Column
    private String id;
    @Column
    private double balance;

    public Account() {
        this.id = IdGenerator.generate();
        this.balance = 100;
    }

    public String toString() {
        return String.format("id : %s\nbalance : %s", this.getId(), this.getBalance());
    }

}
