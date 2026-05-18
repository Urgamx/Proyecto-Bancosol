package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador,Integer> {

    @Query("select c from Colaborador c where c.estado = 1")
    public List<Colaborador> findAllActivos();
}
