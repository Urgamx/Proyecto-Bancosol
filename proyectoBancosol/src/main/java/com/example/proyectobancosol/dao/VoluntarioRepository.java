package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoluntarioRepository extends JpaRepository<Voluntario,Integer> {

    @Query("select v from Voluntario v where v.id = :id")
    public List<Voluntario> findAllByColaborador(@Param("id") Integer id);
}
