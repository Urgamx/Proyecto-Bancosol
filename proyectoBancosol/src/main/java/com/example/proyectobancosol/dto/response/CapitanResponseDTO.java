package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapitanResponseDTO {
    private Integer id;
    private String nombreCompleto;
    private String email;
    private Integer activo;
    private List<String> nombresCampanas;
    private List<String> nombresTiendas;
}
