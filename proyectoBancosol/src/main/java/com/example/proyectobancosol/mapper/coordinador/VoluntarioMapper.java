package com.example.proyectobancosol.mapper.coordinador;

import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.dto.response.VoluntarioDTO;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.mapper.MapperDTO;
import org.springframework.stereotype.Component;

/**
 * Clase que representa la clase VoluntarioMapper.
 *
 * Autores:
 * - David Vilaseca Pareja: 50%
 *
 */

@Component
public class VoluntarioMapper extends MapperDTO<VoluntarioDTO, Voluntario> {

    public VoluntarioDTO toDTO(Voluntario entity) {
        VoluntarioDTO dto = new VoluntarioDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNombre(entity.getNombre());
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
