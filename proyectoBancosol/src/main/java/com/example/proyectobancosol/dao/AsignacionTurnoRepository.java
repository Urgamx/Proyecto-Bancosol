package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.AsignacionTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionTurnoRepository extends JpaRepository<AsignacionTurno,Integer> {
    @Query("SELECT a FROM AsignacionTurno a WHERE a.idTienda.id = :idTienda")
    List<AsignacionTurno> findAsignacionesByTiendaId(@Param("idTienda") Integer idTienda);
}
