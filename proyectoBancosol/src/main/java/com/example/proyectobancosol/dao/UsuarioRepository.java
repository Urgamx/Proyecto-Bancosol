package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmailAndActivo(String email, Integer activo);

    @Query("select u from Usuario u join u.idRol r where r.id= 3")
    public List<Usuario> findCoordinador();
}
