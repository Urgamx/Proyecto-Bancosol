package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * recibe datos de coordinadores
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinadorRequestDTO {

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String password;
    private Integer activo;
}