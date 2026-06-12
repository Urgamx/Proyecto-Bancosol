package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionBasicaResponseDTO {

    private String modulo;
    private String validacion;
    private Long total;
    private String estado;
    private String detalle;
}