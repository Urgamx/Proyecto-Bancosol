package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface UsuarioColaboradorRepository extends JpaRepository<UsuarioColaborador, UsuarioColaboradorId> {

    @Query(" select c from UsuarioColaborador uc join uc.colaborador c join uc.usuario u where lower(c.localidad) like lower(concat('%', :localidad, '%')) and lower(c.zonaGeografica) like lower(concat('%', :zonaGeo, '%'))")
    public List<Colaborador> findByZonaLocalidad(@Param("zonaGeo") String zonaGeo,
                                                 @Param("localidad") String localidad);

    @Query(" select c from UsuarioColaborador uc join uc.colaborador c join uc.usuario u where lower(c.localidad) like lower(concat('%', :localidad, '%')) and lower(c.zonaGeografica) like lower(concat('%', :zonaGeo, '%')) and u.id = :coordinadorId")
    public List<Colaborador> findByZonaLocalidadCoorId(@Param("zonaGeo") String zonaGeo,
                                                       @Param("localidad") String localidad,
                                                       @Param("coordinadorId") Integer coordinadorId);

    @Query("select uc from UsuarioColaborador uc join fetch uc.usuario u where uc.colaborador.id = :idColaborador order by u.nombreCompleto")
    List<UsuarioColaborador> findByColaboradorIdConUsuario(@Param("idColaborador") Integer idColaborador);

    @Query("select uc from UsuarioColaborador uc join fetch uc.colaborador c where uc.usuario.id = :usuarioId order by c.nombreEntidad")
    List<UsuarioColaborador> findColaboradoresAsignadosByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("select uc.colaborador.id from UsuarioColaborador uc where uc.usuario.id = :usuarioId")
    List<Integer> findIdsColaboradoresByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Modifying
    @Query("delete from UsuarioColaborador uc where uc.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Integer usuarioId);
}