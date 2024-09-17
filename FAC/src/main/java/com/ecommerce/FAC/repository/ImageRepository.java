package com.ecommerce.FAC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.FAC.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Métodos de consulta personalizados, se necessário
}