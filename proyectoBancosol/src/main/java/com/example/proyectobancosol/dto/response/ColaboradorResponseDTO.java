package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * datos usados para colaboradores
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorResponseDTO {

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
    private String estado;
    private String coordinadoresAsignados;
    private Long voluntarios;
    private Long turnos;
}