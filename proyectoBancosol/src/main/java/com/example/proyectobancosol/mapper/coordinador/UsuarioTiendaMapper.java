package com.example.proyectobancosol.mapper.coordinador;

import com.example.proyectobancosol.dto.response.UsuarioTiendaDTO;
import com.example.proyectobancosol.entity.UsuarioTienda;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioTiendaMapper extends MapperDTO<UsuarioTiendaDTO,UsuarioTienda> {

    public UsuarioTiendaDTO toDTO(UsuarioTienda entity){
        UsuarioTiendaDTO dto = new UsuarioTiendaDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setTiendaId(entity.getTienda().getId());
        return dto;
    }
}
