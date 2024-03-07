package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
@Entity
public class Account {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(mappedBy = "account")
    private User user;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
