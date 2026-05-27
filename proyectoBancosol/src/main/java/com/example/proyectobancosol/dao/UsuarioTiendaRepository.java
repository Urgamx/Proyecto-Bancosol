package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.UsuarioTienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioTiendaRepository extends JpaRepository<UsuarioTienda, Integer> {

    @Query("SELECT ut FROM UsuarioTienda ut WHERE ut.usuario.id = :usuarioId")
    Optional<UsuarioTienda> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
