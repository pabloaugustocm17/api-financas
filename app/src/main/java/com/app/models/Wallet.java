package com.app.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double amount;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.REFRESH)
    private User user;

    public Wallet(){}

    public Wallet(User user){
        this.user = user;
    }

    public UUID getId(){
        return this.id;
    }

}
