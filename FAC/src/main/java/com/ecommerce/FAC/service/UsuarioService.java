package com.ecommerce.FAC.service;

import java.util.List;
import java.util.regex.Pattern;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.ecommerce.FAC.model.Usuario;
import com.ecommerce.FAC.repository.UsuarioRepository;
 
@Service
public class UsuarioService {
 
    @Autowired
    private UsuarioRepository usuarioRepository;
 
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
 
    public boolean validarSenha(String senhaDigitada, String senhaArmazenada) {
        return passwordEncoder.matches(senhaDigitada, senhaArmazenada);
    }
 
    public Usuario salvarSenhaUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
 
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
 
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
 
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
 
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
 
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
 
    public boolean validarCPF(String cpf) {
        // Implementação simples de validação de CPF
        String cpfPattern = "^[0-9]{11}$";
   
        // Verifica se o formato do CPF é válido
        if (!Pattern.matches(cpfPattern, cpf)) {
            return false;
        }
   
        // Verifica se o CPF já está cadastrado no banco de dados
        return !usuarioRepository.existsByCpf(cpf);
    }
}