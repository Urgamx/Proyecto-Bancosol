package com.example.proyectobancosol.mapper.coordinador;

import com.example.proyectobancosol.dto.response.VoluntarioDTO;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class VoluntarioMapper extends MapperDTO<VoluntarioDTO, Voluntario> {

    public VoluntarioDTO toDTO(Voluntario entity) {
        VoluntarioDTO dto = new VoluntarioDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setIdColaborador(entity.getIdColaborador().getId());
        dto.setNombre(entity.getNombre());
        dto.setTelefono(entity.getTelefono());
        return dto;
    }
}
