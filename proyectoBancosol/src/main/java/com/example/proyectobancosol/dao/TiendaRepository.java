package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TiendaRepository extends JpaRepository<Tienda,Integer> {

    @Query("SELECT ut.tienda FROM UsuarioTienda ut WHERE ut.usuario.id = :usuarioId")
    List<Tienda> findTiendasByUsuarioId(@Param("usuarioId") Integer usuarioId);

}
