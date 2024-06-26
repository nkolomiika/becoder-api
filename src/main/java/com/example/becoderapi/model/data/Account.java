package com.example.becoderapi.model.data;

import com.example.becoderapi.utils.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table
@Builder
public class Account {

    @Id
    @Column(nullable = false)
    private String id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance;

    /*public Account() {
        this.id = IdGenerator.generate();
        this.balance = 0;
    }*/

    public Account(String login, String password) {
        this.id = IdGenerator.generate();
        this.login = login;
        this.password = password;
        this.balance = 10000;
    }

    public Account() {

    }

    public String toString() {
        return String.format("{id : %s, balance : %s}", this.getId(), this.getBalance());
    }
}
