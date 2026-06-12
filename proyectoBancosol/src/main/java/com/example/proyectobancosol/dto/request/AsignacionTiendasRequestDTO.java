package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionTiendasRequestDTO {

    private Integer idCoordinador;
    private List<Integer> idsTiendas = new ArrayList<>();
}