package com.example.proyectobancosol.mapper.coordinador;

import com.example.proyectobancosol.dto.response.UsuarioColaboradorDTO;
import com.example.proyectobancosol.entity.UsuarioColaborador;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioColaboradorMapper extends MapperDTO<UsuarioColaboradorDTO, UsuarioColaborador> {

    public UsuarioColaboradorDTO toDTO(UsuarioColaborador entity) {
        UsuarioColaboradorDTO dto = new UsuarioColaboradorDTO();
        dto.setColaboradorId(entity.getColaborador().getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
