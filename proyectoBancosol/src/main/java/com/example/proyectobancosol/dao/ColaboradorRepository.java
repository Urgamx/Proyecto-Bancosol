package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    @Query("select c from Colaborador c where c.estado = 1")
    public List<Colaborador> findAllActivos();

    List<Colaborador> findAllByOrderByNombreEntidadAsc();

    List<Colaborador> findByEstadoOrderByNombreEntidadAsc(Integer estado);

    @Query("""
            select count(c) > 0
            from Colaborador c
            where lower(c.email) = lower(:email)
            and (:idColaborador is null or c.id <> :idColaborador)
            """)
    boolean existsEmailDuplicado(@Param("email") String email,
                                 @Param("idColaborador") Integer idColaborador);

    @Query("select count(v) from Voluntario v where v.idColaborador.id = :idColaborador")
    Long countVoluntariosByColaborador(@Param("idColaborador") Integer idColaborador);

    @Query(value = """
            select count(*)
            from asignacion_turnos a
            join voluntario v on a.id_voluntario = v.id_voluntario
            where v.id_colaborador = :idColaborador
            """, nativeQuery = true)
    Long countTurnosByColaborador(@Param("idColaborador") Integer idColaborador);

    @Query("select count(uc) from UsuarioColaborador uc where uc.colaborador.id = :idColaborador")
    Long countUsuariosByColaborador(@Param("idColaborador") Integer idColaborador);
}