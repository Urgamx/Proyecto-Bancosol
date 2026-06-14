package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * datos de sesion
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioSesionDTO {
    private Integer idUsuario;
    private String email;
    private String nombreCompleto;
    private String rol;
    
}
