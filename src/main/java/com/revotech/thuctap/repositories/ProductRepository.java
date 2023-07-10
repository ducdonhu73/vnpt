package com.revotech.thuctap.repositories;

import com.revotech.thuctap.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    Product findByCode(String code);
    boolean existsByCode(String code);
}
