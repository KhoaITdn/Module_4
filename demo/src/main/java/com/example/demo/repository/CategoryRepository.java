package com.example.demo.repository;

import com.example.demo.model.Category; // Đảm bảo nhập đúng mô hình Category
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}