package com.ecommerce.FAC.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity(name = "produtos")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double avaliacao;
    private String descricaoDetalhada;
    private double preco;
    private int qtdEstoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imagens;
}