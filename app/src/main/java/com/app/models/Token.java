package com.app.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private LocalDateTime last_access;

    public Token(){}

    public Token(String email) {
        this.email = email;
        this.last_access = LocalDateTime.now();
    }

    public UUID getId(){
        return this.id;
    }
}
