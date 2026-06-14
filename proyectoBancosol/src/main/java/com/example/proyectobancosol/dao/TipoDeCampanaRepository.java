package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.TipoDeCampana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * lista de camapanas ordenadas por nombre
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

public interface TipoDeCampanaRepository extends JpaRepository<TipoDeCampana, Integer> {

    List<TipoDeCampana> findAllByOrderByNombreAsc();
}