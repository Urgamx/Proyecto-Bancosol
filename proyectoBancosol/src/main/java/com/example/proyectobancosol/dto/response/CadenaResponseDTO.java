package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadenaResponseDTO {

    private Integer id;
    private String nombre;
    private String personaContacto;
    private String telefonoContacto;
}