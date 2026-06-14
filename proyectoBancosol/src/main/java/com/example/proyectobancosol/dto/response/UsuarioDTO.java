package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clase UsuarioDTO.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private Integer idRol;
    private String email;
    private String password;
    private String nombreCompleto;
    private Integer activo;
}
