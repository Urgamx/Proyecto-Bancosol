package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador", nullable = false)
    private Integer id;

    @Column(name = "nombre_entidad", nullable = false, length = 150)
    private String nombreEntidad;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "contacto_nom")
    private String contactoNom;

    @Column(name = "contacto_tlf", length = 20)
    private String contactoTlf;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "zona_geografica")
    private String zonaGeografica;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @Column(name = "cod_postal")
    private String codPostal;

    @Column(name = "estado", nullable = false)
    private Integer estado;

}