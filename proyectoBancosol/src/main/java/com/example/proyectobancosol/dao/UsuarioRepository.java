package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmailAndActivo(String email, Integer activo);
}
