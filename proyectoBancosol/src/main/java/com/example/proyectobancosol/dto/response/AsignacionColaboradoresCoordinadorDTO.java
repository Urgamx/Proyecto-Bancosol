package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * datos de relacion de coord y colaboradores
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionColaboradoresCoordinadorDTO {

    private Integer idCoordinador;
    private String nombreCompleto;
    private String email;
    private String colaboradoresAsignados;
}