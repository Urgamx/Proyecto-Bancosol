package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TiendaRepository extends JpaRepository<Tienda, Integer> {

    @Query("SELECT ut.tienda FROM UsuarioTienda ut WHERE ut.usuario.id = :usuarioId")
    List<Tienda> findTiendasByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("select t from Tienda t join fetch t.idCadena order by t.idCadena.nombre, t.nombre")
    List<Tienda> findAllConCadena();

    @Query("select coalesce(max(t.id), 0) from Tienda t")
    Integer findMaxId();

    @Query("""
            select count(t) > 0
            from Tienda t
            where t.idCadena.id = :idCadena
            and lower(t.nombre) = lower(:nombre)
            and lower(t.direccion) = lower(:direccion)
            and (:idTienda is null or t.id <> :idTienda)
            """)
    boolean existsDuplicada(@Param("idCadena") Integer idCadena,
                            @Param("nombre") String nombre,
                            @Param("direccion") String direccion,
                            @Param("idTienda") Integer idTienda);

    @Query("select count(tc) from TiendaCampana tc where tc.tienda.id = :idTienda")
    Long countCampanasByTienda(@Param("idTienda") Integer idTienda);

    @Query("select count(ut) from UsuarioTienda ut where ut.tienda.id = :idTienda")
    Long countUsuariosByTienda(@Param("idTienda") Integer idTienda);

    @Query(value = "select count(*) from asignacion_turnos where id_tienda = :idTienda", nativeQuery = true)
    Long countTurnosByTienda(@Param("idTienda") Integer idTienda);
}