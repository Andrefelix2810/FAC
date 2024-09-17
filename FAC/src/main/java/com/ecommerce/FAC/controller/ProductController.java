package com.ecommerce.FAC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.FAC.model.Image;
import com.ecommerce.FAC.model.Product;
import com.ecommerce.FAC.repository.ProductRepository;
import com.ecommerce.FAC.repository.ImageRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductController {
    private static String caminhoImagens = "C:\\Users\\andre\\OneDrive\\Documentos\\imagens\\";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Product> cadastrar(
        @RequestParam("nome") String nome,
        @RequestParam("avaliacao") double avaliacao,
        @RequestParam("descricaoDetalhada") String descricaoDetalhada,
        @RequestParam("preco") double preco,
        @RequestParam("qtdEstoque") int qtdEstoque,
        @RequestPart("imagens") MultipartFile[] imagens,
        @RequestParam("imagemPrincipal") int imagemPrincipalIndex) {
    
        try {
            Product product = new Product();
            product.setNome(nome);
            product.setAvaliacao(avaliacao);
            product.setDescricaoDetalhada(descricaoDetalhada);
            product.setPreco(preco);
            product.setQtdEstoque(qtdEstoque);
    
            List<Image> listaImagens = new ArrayList<>();
    
            for (int i = 0; i < imagens.length; i++) {
                MultipartFile imagem = imagens[i];
                if (!imagem.isEmpty()) {
                    String novoNome = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
                    Path caminhoArquivo = Paths.get(caminhoImagens + novoNome);
                    Files.createDirectories(caminhoArquivo.getParent());
                    Files.write(caminhoArquivo, imagem.getBytes());
    
                    Image img = new Image();
                    img.setCaminho(caminhoArquivo.toString());
                    img.setPrincipal(i == imagemPrincipalIndex);
                    img.setProduto(product);
                    listaImagens.add(img);
                }
            }
    
            product.setImagens(listaImagens);
            Product produtoSalvo = productRepository.save(product);
            imageRepository.saveAll(listaImagens);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}