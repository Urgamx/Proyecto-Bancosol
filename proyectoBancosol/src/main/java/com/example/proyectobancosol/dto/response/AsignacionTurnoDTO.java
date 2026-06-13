package com.example.proyectobancosol.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionTurnoDTO {
    private Integer idAsignacion;
    private String dia;
    private String franja;
    private String horaInicio;
    private String horaFin;

    private VoluntarioDTO voluntarioDTO;

    private CampanaResponseDTO campanaResponseDTO;

    private IncidenciaDTO incidenciaDTO;
}
