package com.example.proyectobancosol.dto.response;


import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
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

    private TiendaRequestDTO tiendaResponseDTO;

    private ColaboradorRequestDTO colaboradorRequestDTO;


}
