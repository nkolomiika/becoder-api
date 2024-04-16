package com.example.becoderapi.model;

import com.example.becoderapi.model.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Component
public class Account {

    private String id;
    private double balance;

    public Account() {
        this.id = IdGenerator.generate();
        this.balance = 100;
    }

}
