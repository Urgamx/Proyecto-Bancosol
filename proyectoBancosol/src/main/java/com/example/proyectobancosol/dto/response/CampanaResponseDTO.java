package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampanaResponseDTO {

    private Integer id;
    private String tipoCampana;
    private Integer fecha;
    private String fechaTexto;
    private String activo;
    private String cadenasParticipantes;
}