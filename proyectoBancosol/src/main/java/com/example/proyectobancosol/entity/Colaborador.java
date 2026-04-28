package com.example.proyectobancosol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
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

}