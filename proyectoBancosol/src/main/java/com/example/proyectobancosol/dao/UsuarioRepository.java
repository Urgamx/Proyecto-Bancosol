package com.example.proyectobancosol.dao;

import com.example.proyectobancosol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Spring Boot es tan listo que al llamar al método así,
    // él solo crea la consulta SQL: SELECT * FROM USUARIO WHERE email = ? AND password = ?
    Usuario findByEmailAndPassword(String email, String password);

}