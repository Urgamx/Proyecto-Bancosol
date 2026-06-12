package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.CampanaCadena;
import com.example.proyectobancosol.entity.CampanaCadenaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampanaCadenaRepository extends JpaRepository<CampanaCadena, CampanaCadenaId> {

    @Query("select cc from CampanaCadena cc join fetch cc.cadena where cc.campana.id = :idCampana order by cc.cadena.nombre")
    List<CampanaCadena> findByCampanaIdConCadena(@Param("idCampana") Integer idCampana);

    @Query("select cc.cadena.id from CampanaCadena cc where cc.campana.id = :idCampana")
    List<Integer> findIdsCadenasByCampanaId(@Param("idCampana") Integer idCampana);

    @Modifying
    @Query("delete from CampanaCadena cc where cc.campana.id = :idCampana")
    void deleteByCampanaId(@Param("idCampana") Integer idCampana);
}