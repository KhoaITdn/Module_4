package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByCategory(Category category); // Tham chiếu từ mô hình Category

    List<Product> findByStartingPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByStatus(Status status); // Tham chiếu từ mô hình Status
}
