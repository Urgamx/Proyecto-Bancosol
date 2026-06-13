package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndActivo(String email, Integer activo);

    @Query("select u from Usuario u join u.idRol r where r.id= 3 and u.activo = 1")
    public List<Usuario> findCoordinador();

    @Query("select u from Usuario u join u.idRol r where r.id= 5 and u.activo = 1")
    public List<Usuario> findCapitan();

    boolean existsByEmailIgnoreCase(String email);

    @Query("select coalesce(max(u.id), 0) from Usuario u")
    Integer findMaxId();

    @Query("select u from Usuario u join fetch u.idRol where u.idRol.nombre = :nombreRol order by u.nombreCompleto")
    List<Usuario> findByRolNombre(@Param("nombreRol") String nombreRol);

    @Query("""
            select count(u) > 0
            from Usuario u
            where lower(u.email) = lower(:email)
            and (:idUsuario is null or u.id <> :idUsuario)
            """)
    boolean existsEmailDuplicado(@Param("email") String email,
                                 @Param("idUsuario") Integer idUsuario);

    @Query("select count(ut) from UsuarioTienda ut where ut.usuario.id = :idUsuario")
    Long countTiendasByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("select count(uc) from UsuarioColaborador uc where uc.usuario.id = :idUsuario")
    Long countColaboradoresByUsuario(@Param("idUsuario") Integer idUsuario);

}