package com.example.proyectobancosol.service.common;

import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(String email, String password){
        return usuarioRepository.findByEmailAndActivo(email, 1)
                .filter(usuario -> usuario.getPassword().equals(password));
    }

}
