package com.example.proyectobancosol.dto.response;

import com.example.proyectobancosol.entity.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDTO {
    private Integer id;
    private Integer idColaborador;
    private String nombre;
    private String telefono;
    private String email;
}
