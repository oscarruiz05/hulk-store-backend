package com.example.hulk_store_backend.repository;

import com.example.hulk_store_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
