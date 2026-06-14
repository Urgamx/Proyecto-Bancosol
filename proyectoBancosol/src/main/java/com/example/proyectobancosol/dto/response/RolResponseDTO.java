package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clase RolResponseDTO.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
}
