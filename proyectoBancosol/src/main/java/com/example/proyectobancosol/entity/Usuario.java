package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "activo")
    private Integer activo;
}