package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * recibe datos de colaboradores
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorRequestDTO {

    private Integer id;
    private String nombreEntidad;
    private String email;
    private String contactoNom;
    private String contactoTlf;
    private String domicilio;
    private String localidad;
    private String zonaGeografica;
    private String observaciones;
    private String codPostal;
    private Integer estado;
}