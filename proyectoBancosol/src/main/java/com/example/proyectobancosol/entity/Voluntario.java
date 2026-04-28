package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "VOLUNTARIO")
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voluntario")
    private Integer idVoluntario;

    @Column(name = "id_colaborador")
    private Integer idColaborador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;
}
