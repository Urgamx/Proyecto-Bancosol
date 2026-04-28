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
@Table(name = "cadenas")
public class Cadena {
    @Id
    @Column(name = "id_cadena", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "persona_contacto", length = 150)
    private String personaContacto;

    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;

}