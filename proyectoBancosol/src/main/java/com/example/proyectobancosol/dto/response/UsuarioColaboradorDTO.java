package com.example.proyectobancosol.dto.response;

import com.example.proyectobancosol.entity.UsuarioColaboradorId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioColaboradorDTO {
    private UsuarioColaboradorId id;
    private Integer usuarioId;
    private Integer colaboradorId;
}
