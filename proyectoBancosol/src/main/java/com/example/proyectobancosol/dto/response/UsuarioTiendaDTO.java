package com.example.proyectobancosol.dto.response;


import com.example.proyectobancosol.entity.UsuarioTiendaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTiendaDTO {
    private UsuarioTiendaId id;
    private Integer usuarioId;
    private Integer tiendaId;
}
