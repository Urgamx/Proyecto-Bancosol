package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * datos de cadena
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadenaResponseDTO {

    private Integer id;
    private String nombre;
    private String personaContacto;
    private String telefonoContacto;
}