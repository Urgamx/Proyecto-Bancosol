package com.example.proyectobancosol.service.common;

import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.entity.Usuario;
import org.springframework.stereotype.Service;
import com.example.proyectobancosol.dto.request.LoginRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> login(LoginRequestDTO loginRequestDTO) {

        if (loginRequestDTO == null
                || loginRequestDTO.getEmail() == null
                || loginRequestDTO.getPassword() == null) {
            return Optional.empty();
        }

        String email = loginRequestDTO.getEmail().trim();
        String password = loginRequestDTO.getPassword();

        return usuarioRepository.findByEmailAndActivo(email, 1)
                .filter(usuario -> usuario.getPassword().equals(password));
    }

}
