package com.example.proyectobancosol.mapper.capitan;

import com.example.proyectobancosol.dto.request.ColaboradorRequestDTO;
import com.example.proyectobancosol.dto.request.TiendaRequestDTO;
import com.example.proyectobancosol.dto.response.*;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.mapper.MapperDTO;
import com.example.proyectobancosol.mapper.admin.CampanaAdminMapper;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import com.example.proyectobancosol.mapper.admin.TiendaAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsignacionTurnoCapitanMapper extends MapperDTO<AsignacionTurnoDTO, AsignacionTurno> {

    private final VoluntarioCapitanMapper voluntarioMapper;
    private final CampanaAdminMapper campanaAdminMapper;
    private final IncidenciaCapitanMapper incidenciaMapper;
    private final TiendaAdminMapper tiendaAdminMapper;
    private final ColaboradorAdminMapper colaboradorAdminMapper;

    @Override
    public AsignacionTurnoDTO toDTO(AsignacionTurno asignacionTurno) {

        VoluntarioDTO voluntarioDTO = voluntarioMapper.toDTO(asignacionTurno.getIdVoluntario());
        CampanaResponseDTO campanaResponseDTO = campanaAdminMapper.toDTO(asignacionTurno.getIdCampana());
        TiendaRequestDTO tiendaRequestDTO = tiendaAdminMapper.toRequestDTO(asignacionTurno.getIdTienda());
        ColaboradorRequestDTO colaboradorRequestDTO = colaboradorAdminMapper.toRequestDTO(asignacionTurno.getIdColaborador());

        IncidenciaDTO incidenciaDTO = null;
        if (asignacionTurno.getIncidencia() != null) {
            incidenciaDTO = incidenciaMapper.toDTO(asignacionTurno.getIncidencia());
        }

        return new AsignacionTurnoDTO(
                asignacionTurno.getId(),
                asignacionTurno.getDia(),
                asignacionTurno.getFranja(),
                asignacionTurno.getHoraInicio() != null ? asignacionTurno.getHoraInicio().toString() : null,
                asignacionTurno.getHoraFin() != null ? asignacionTurno.getHoraFin().toString() : null,
                voluntarioDTO,
                campanaResponseDTO,
                incidenciaDTO,
                tiendaRequestDTO,
                colaboradorRequestDTO
        );
    }
}