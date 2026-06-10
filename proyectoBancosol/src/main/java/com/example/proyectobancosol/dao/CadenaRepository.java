package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Cadena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CadenaRepository extends JpaRepository<Cadena, Integer> {

    List<Cadena> findAllByOrderByNombreAsc();

    boolean existsByNombreIgnoreCase(String nombre);

    @Query("select coalesce(max(c.id), 0) from Cadena c")
    Integer findMaxId();

    @Query("select count(t) from Tienda t where t.idCadena.id = :idCadena")
    Long countTiendasByCadena(@Param("idCadena") Integer idCadena);

    @Query("select count(cc) from CampanaCadena cc where cc.cadena.id = :idCadena")
    Long countCampanasByCadena(@Param("idCadena") Integer idCadena);
}