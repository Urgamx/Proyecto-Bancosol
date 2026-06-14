package com.example.proyectobancosol.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * recibe los datos para asignar colaboradores a coord
 *
 * Autores:
 * - Jesus Moreno Carmona: 100%
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionColaboradoresRequestDTO {

    private Integer idCoordinador;
    private List<Integer> idsColaboradores = new ArrayList<>();
}