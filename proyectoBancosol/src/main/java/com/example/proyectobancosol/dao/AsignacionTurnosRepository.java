package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.AsignacionTurnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionTurnosRepository extends JpaRepository<AsignacionTurnos, Integer> {
}