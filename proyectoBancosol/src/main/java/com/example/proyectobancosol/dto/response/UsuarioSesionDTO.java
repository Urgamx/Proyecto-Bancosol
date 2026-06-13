package com.example.proyectobancosol.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioSesionDTO {    private Integer idUsuario;
    private String email;
    private String nombreCompleto;
    private String rol;
    
}
