package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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