package com.example.proyectobancosol.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TIENDA")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private Integer idTienda;

    @Column(name = "id_cadena")
    private Integer idCadena;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "cod_postal")
    private String codPostal;
}