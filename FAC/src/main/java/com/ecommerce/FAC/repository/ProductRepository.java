package com.ecommerce.FAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.FAC.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos de consulta personalizados, se necessário
}