package com.ecommerce.FAC.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private Product produto;
}
