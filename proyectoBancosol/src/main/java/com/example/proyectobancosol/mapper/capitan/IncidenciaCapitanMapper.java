package com.example.proyectobancosol.mapper.capitan;

import com.example.proyectobancosol.dto.response.IncidenciaDTO;
import com.example.proyectobancosol.entity.Incidencia;
import com.example.proyectobancosol.mapper.MapperDTO;

import org.springframework.stereotype.Component;

@Component
public class IncidenciaCapitanMapper extends MapperDTO<IncidenciaDTO, Incidencia> {
    @Override
    public IncidenciaDTO toDTO(Incidencia incidencia) {
        return new IncidenciaDTO(incidencia.getId(), incidencia.getAsignacion().getId(),incidencia.getDescripcion());
    }

}
