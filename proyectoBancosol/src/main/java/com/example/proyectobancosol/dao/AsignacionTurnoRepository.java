package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.AsignacionTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionTurnoRepository extends JpaRepository<AsignacionTurno,Integer> {
    @Query("SELECT a FROM AsignacionTurno a WHERE a.idTienda.id = :idTienda")
    List<AsignacionTurno> findAsignacionesByTiendaId(@Param("idTienda") Integer idTienda);

    @Query("select a from AsignacionTurno a join a.idTienda t where t.idCadena.id = :cadenaId and t.localidad like concat('%', :localidad, '%') ")
    List<AsignacionTurno> findByCadenaLocalidad(@Param("cadenaId")Integer cadenaId,
                                                @Param("localidad")String localidad);

    @Query("select a from AsignacionTurno a join a.idTienda t where t.localidad like concat('%', :localidad, '%') ")
    List<AsignacionTurno> findByLocalidad(@Param("localidad")String localidad);
}
