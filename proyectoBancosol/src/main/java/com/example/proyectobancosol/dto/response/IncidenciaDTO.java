package com.example.proyectobancosol.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clase IncidenciaDTO.
 *
 * Autores:
 * - David Vilaseca Pareja: 33%
 * - Carlos Sánchez: 67%
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciaDTO {
    private Integer idIncidencia;
    private Integer idAsignacionTurno;
    private String descripcion;

}
