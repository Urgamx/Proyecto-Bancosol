package com.example.proyectobancosol.mapper.coordinador;

import com.example.proyectobancosol.dto.response.UsuarioDTO;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper extends MapperDTO<UsuarioDTO, Usuario> {

    public UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setActivo(entity.getActivo());
        dto.setPassword(entity.getPassword());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setIdRol(entity.getIdRol().getId());
        return dto;
    }
}
