package com.example.hulk_store_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "categories")
@Entity
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String name;
        @Column(columnDefinition = "TEXT", nullable = false)
        private String description;
}