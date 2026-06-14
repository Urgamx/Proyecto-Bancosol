package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.UsuarioTienda;
import com.example.proyectobancosol.entity.UsuarioTiendaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


/**
 * querys para busqueda de usuarios relacionado con tiendas
 *
 * Autores:
 * - Jesus Moreno Carmona: 70%
 * - IA: 30%
 *
 */

public interface UsuarioTiendaRepository extends JpaRepository<UsuarioTienda, UsuarioTiendaId> {

    @Query("SELECT ut FROM UsuarioTienda ut WHERE ut.usuario.id = :usuarioId")
    Optional<UsuarioTienda> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("select ut from UsuarioTienda ut join fetch ut.tienda t join fetch t.idCadena where ut.usuario.id = :usuarioId order by t.idCadena.nombre, t.nombre")
    List<UsuarioTienda> findTiendasAsignadasByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("select ut.tienda.id from UsuarioTienda ut where ut.usuario.id = :usuarioId")
    List<Integer> findIdsTiendasByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Modifying
    @Query("delete from UsuarioTienda ut where ut.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Integer usuarioId);
}