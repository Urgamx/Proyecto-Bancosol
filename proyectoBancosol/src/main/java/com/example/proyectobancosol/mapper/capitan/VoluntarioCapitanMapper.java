package com.example.proyectobancosol.mapper.capitan;

import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.dto.response.VoluntarioDTO;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

@Component
public class VoluntarioCapitanMapper extends MapperDTO<VoluntarioDTO, Voluntario> {

    @Override
    public VoluntarioDTO toDTO(Voluntario entity) {
        if (entity == null) {
            return null;
        }

        VoluntarioDTO dto = new VoluntarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());

        if (entity.getIdColaborador() != null) {
            ColaboradorResponseDTO colaboradorDTO = new ColaboradorResponseDTO();
            colaboradorDTO.setId(entity.getIdColaborador().getId());
            colaboradorDTO.setNombreEntidad(entity.getIdColaborador().getNombreEntidad());
            colaboradorDTO.setEmail(entity.getIdColaborador().getEmail());
            colaboradorDTO.setContactoTlf(entity.getIdColaborador().getContactoTlf());
            colaboradorDTO.setDomicilio(entity.getIdColaborador().getDomicilio());

            dto.setColaborador(colaboradorDTO);
        }

        return dto;
    }
}