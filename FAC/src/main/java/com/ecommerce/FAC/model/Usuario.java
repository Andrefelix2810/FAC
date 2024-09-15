package com.ecommerce.FAC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
 
@Entity(name = "Usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String nome;
    private String email;
    private String senha;
    private String status;
    private String grupo;
    private String cpf; // Adicionado campo CPF
   
    @Transient // Não será persistido no banco de dados
    private String senhaConfirmacao;
    }