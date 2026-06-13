package com.example.proyectobancosol.dto.request;

import lombok.Data;

@Data
public class IncidenciaRequestDTO {
    private Integer idAsignacion;
    private Integer idTienda;
    private String descripcion;
}