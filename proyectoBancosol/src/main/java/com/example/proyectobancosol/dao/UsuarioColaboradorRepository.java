package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioColaboradorRepository extends JpaRepository<UsuarioColaborador, UsuarioColaboradorId> {
}
