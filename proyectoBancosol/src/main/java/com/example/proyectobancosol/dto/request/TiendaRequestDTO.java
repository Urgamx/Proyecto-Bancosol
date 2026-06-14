package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * recibe datos de tienda
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaRequestDTO {

    private Integer id;
    private Integer idCadena;
    private String nombre;
    private String direccion;
    private String localidad;
    private String codPostal;
}