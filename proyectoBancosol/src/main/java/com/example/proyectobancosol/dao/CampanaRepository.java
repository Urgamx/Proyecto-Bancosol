package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Campana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampanaRepository extends JpaRepository<Campana, Integer> {

    @Query("select cc.campana from CampanaCadena cc where cc.cadena.id = :cadenaId")
    public List<Campana> findByCadena(@Param("cadenaId") Integer cadenaId);
  
    @Query("select c from Campana c join fetch c.tipoDeCampana order by c.fecha desc")
    List<Campana> findAllConTipo();

    @Query("select coalesce(max(c.id), 0) from Campana c")
    Integer findMaxId();

    @Query("""
            select count(c) > 0
            from Campana c
            where c.tipoDeCampana.id = :idTipoCampana
            and c.fecha = :fecha
            and (:idCampana is null or c.id <> :idCampana)
            """)
    boolean existsDuplicada(@Param("idTipoCampana") Integer idTipoCampana,
                            @Param("fecha") Integer fecha,
                            @Param("idCampana") Integer idCampana);

    @Query("select count(tc) from TiendaCampana tc where tc.campana.id = :idCampana")
    Long countTiendasByCampana(@Param("idCampana") Integer idCampana);

    @Query(value = """
            select count(*)
            from asignacion_turnos a
            join tienda_campana tc on tc.id_tienda = a.id_tienda
            where tc.id_campana = :idCampana
            """, nativeQuery = true)
    Long countTurnosByCampana(@Param("idCampana") Integer idCampana);


    @Query("""
        select c.tipoDeCampana.nombre 
        from Campana c 
        where c.usuario.id = :usuarioId
        order by c.fecha desc
       """)
    List<String> findNombresCampanasByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("select c.id from Campana c where c.usuario.id = :usuarioId")
    List<Integer> findIdCampanasByUsuarioId(@Param("usuarioId") Integer usuarioId);

    @Query("""
        select c
        from Campana c
        join fetch c.tipoDeCampana
        where (:idTipoCampana is null or c.tipoDeCampana.id = :idTipoCampana)
        and (:fecha is null or c.fecha = :fecha)
        and (:activo is null or c.activo = :activo)
        order by c.fecha desc
        """)
    List<Campana> findFiltradas(@Param("idTipoCampana") Integer idTipoCampana,
                                @Param("fecha") Integer fecha,
                                @Param("activo") Integer activo);

}

