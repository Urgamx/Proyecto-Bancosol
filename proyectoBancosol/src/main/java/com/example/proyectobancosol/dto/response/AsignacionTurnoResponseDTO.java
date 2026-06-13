package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionTurnoResponseDTO {
    private Integer id;
    private String horario; 
    private String fecha; 
    private VoluntarioResponseDTO voluntario; 
    private String descripcionIncidencia; 
    private String campana;

    public String getNombreCampana() {
        return this.campana;
    }
}