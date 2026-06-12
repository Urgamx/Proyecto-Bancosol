package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionTiendasCoordinadorDTO {

    private Integer idCoordinador;
    private String nombreCompleto;
    private String email;
    private String tiendasAsignadas;
}