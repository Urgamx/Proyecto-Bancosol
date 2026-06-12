package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampanaRequestDTO {

    private Integer id;
    private Integer idTipoCampana;
    private Integer fecha;
    private String fechaFormulario;
    private Integer activo;
    private List<Integer> idsCadenas = new ArrayList<>();
}