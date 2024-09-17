package com.ecommerce.FAC.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private boolean ativo = true; // Novo campo para indicar se o produto está ativo

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> imagens;

    // Getter e Setter para o campo ativo
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}