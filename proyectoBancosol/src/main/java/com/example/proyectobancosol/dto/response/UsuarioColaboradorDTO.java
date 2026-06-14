package com.example.proyectobancosol.dto.response;

import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la clase UsuarioColaboradorDTO.
 *
 * Autores:
 * - David Vilaseca Pareja: 100%
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioColaboradorDTO {
    private UsuarioColaboradorId id;
    private Integer usuarioId;
    private Integer colaboradorId;
}
