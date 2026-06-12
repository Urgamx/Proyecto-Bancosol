package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinadorResponseDTO {

    private Integer id;
    private String nombreCompleto;
    private String email;
    private String activo;
    private Long tiendasAsignadas;
    private Long colaboradoresAsignados;
}