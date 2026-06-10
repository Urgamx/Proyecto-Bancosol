package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Campana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampanaRepository extends JpaRepository<Campana,Integer> {

    @Query("select cc.campana from CampanaCadena cc where cc.cadena.id = :cadenaId")
    public List<Campana> findByCadena(@Param("cadenaId") Integer cadenaId);
}
