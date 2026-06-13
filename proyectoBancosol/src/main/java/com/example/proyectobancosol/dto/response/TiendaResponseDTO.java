package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaResponseDTO {

    private Integer id;
    private String cadena;
    private String nombre;
    private String direccion;
    private String localidad;
    private String codPostal;

    public String getNombreCadena() {
        return cadena;
    }
}