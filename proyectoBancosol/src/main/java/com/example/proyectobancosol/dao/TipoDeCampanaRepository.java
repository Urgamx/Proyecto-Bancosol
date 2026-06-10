package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.TipoDeCampana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoDeCampanaRepository extends JpaRepository<TipoDeCampana, Integer> {

    List<TipoDeCampana> findAllByOrderByNombreAsc();
}