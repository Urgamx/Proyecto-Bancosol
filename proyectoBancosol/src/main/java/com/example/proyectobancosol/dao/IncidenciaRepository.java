/*
Clase que controla la funcionalidad del rol coordinador.
Marta Vegas: 50%
 */

package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {
}
