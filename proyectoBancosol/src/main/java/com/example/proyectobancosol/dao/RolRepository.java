package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * busqueda de roles por nombre
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByNombre(String nombre);
}