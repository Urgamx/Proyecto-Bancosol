package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para la captura de datos de formularios de creación/edición de Capitanes.
 *
 * Autores:
 * - Carlos Sánchez: 100%
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapitanRequestDTO {
    private Integer id;
    private String nombreCompleto;
    private String email;
    private String password;
    private Integer activo;
    private List<Integer> idCampanasSeleccionadas;
    private List<Integer> idTiendasSeleccionadas;
}
