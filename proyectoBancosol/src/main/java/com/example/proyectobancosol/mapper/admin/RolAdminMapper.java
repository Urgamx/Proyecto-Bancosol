package com.example.proyectobancosol.mapper.admin;

import com.example.proyectobancosol.dto.response.RolResponseDTO;
import com.example.proyectobancosol.entity.Rol;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class RolAdminMapper extends MapperDTO<RolResponseDTO, Rol> {

    public RolResponseDTO toDTO(Rol entity) {
        RolResponseDTO dto = new RolResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

}
