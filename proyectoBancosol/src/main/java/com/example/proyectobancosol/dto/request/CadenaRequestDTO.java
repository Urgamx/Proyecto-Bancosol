package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadenaRequestDTO {

    private Integer id;
    private String nombre;
    private String personaContacto;
    private String telefonoContacto;
}