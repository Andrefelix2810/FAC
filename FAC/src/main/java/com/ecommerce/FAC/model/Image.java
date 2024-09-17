package com.ecommerce.FAC.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "imagens")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caminho;
    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Product produto;
}