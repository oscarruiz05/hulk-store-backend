package com.example.hulk_store_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false, unique = true)
        private String email;
        private String password;
        @Column(nullable = false)
        private String role;
        @Column(name = "credit_card", unique = true)
        private String creditCard;
}
