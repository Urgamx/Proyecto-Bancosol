package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * datos usados para validaciones en el panel admin
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionBasicaResponseDTO {

    private String modulo;
    private String validacion;
    private Long total;
    private String estado;
    private String detalle;
}