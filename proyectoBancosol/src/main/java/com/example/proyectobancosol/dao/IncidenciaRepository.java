
/**
 * Respository de Incidencias
 * Autores:
 * - Jesus Moreno Carmona: 50%
 * - Marta Vegas: 50%
 */


package com.example.proyectobancosol.dao;


import com.example.proyectobancosol.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {

    @Modifying
    @Query("delete from Incidencia i where i.asignacion.id = :idAsignacion")
    void deleteByAsignacionId(@Param("idAsignacion") Integer idAsignacion);
}