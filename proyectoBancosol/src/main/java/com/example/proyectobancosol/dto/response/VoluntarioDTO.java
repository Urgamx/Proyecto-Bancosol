package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clase VoluntarioDTO.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDTO {
    private Integer id;
    private ColaboradorResponseDTO Colaborador;
    private String nombre;
    private String telefono;
    private String email;
}
