package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * datos de relacion entre tiendas y coordinadores
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionTiendasCoordinadorDTO {

    private Integer idCoordinador;
    private String nombreCompleto;
    private String email;
    private String tiendasAsignadas;
}