package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioColaboradorRepository extends JpaRepository<UsuarioColaborador, UsuarioColaboradorId> {

    @Query(" select c from UsuarioColaborador uc join uc.colaborador c join uc.usuario u where lower(c.localidad) like lower(concat('%', :localidad, '%')) and lower(c.zonaGeografica) like lower(concat('%', :zonaGeo, '%'))")
    public List<Colaborador> findByZonaLocalidad(@Param("zonaGeo") String zonaGeo,
                                                 @Param("localidad") String localidad);

    @Query(" select c from UsuarioColaborador uc join uc.colaborador c join uc.usuario u where lower(c.localidad) like lower(concat('%', :localidad, '%')) and lower(c.zonaGeografica) like lower(concat('%', :zonaGeo, '%')) and u.id = :coordinadorId")
    public List<Colaborador> findByZonaLocalidadCoorId(@Param("zonaGeo") String zonaGeo,
                                                 @Param("localidad") String localidad,
                                                 @Param("coordinadorId") Integer coordinadorId);
}
