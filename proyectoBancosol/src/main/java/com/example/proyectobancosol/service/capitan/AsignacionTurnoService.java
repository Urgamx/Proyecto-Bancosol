package com.example.proyectobancosol.service.capitan;

import com.example.proyectobancosol.dao.*;
import com.example.proyectobancosol.dto.response.AsignacionTurnoDTO;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.mapper.capitan.AsignacionTurnoCapitanMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

/**
 * Clase que representa la clase AsignacionTurnoService.
 *
 * Autores:
 * - IA Generatiova: 50%
 * - David Vilaseca Pareja: 25%
 * - Jesus Moreno Carmona: 10%
 *
 */

@Service
@AllArgsConstructor
public class AsignacionTurnoService {

    private final AsignacionTurnoRepository asignacionTurnoRepository;
    private final CampanaRepository campanaRepository;
    private final AsignacionTurnoCapitanMapper asignacionTurnoCapitanMapper;
    private final IncidenciaRepository incidenciaRepository;
    private final TiendaRepository tiendaRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final VoluntarioRepository voluntarioRepository;

    public List<AsignacionTurnoDTO> ListarAsignacionTurnos(){
        return this.ListarAsignacionTurnos(null);
    }

    public List<AsignacionTurnoDTO> ListarAsignacionTurnos(Integer idTienda){
        List<AsignacionTurnoDTO> turnos;

        if(idTienda == null){
            turnos = asignacionTurnoCapitanMapper.toDTOList(this.asignacionTurnoRepository.findAll());
        }else{
            turnos= asignacionTurnoCapitanMapper.toDTOList(this.asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda));
        }

        return turnos;
    }

    public AsignacionTurnoDTO findById(Integer id) { return asignacionTurnoCapitanMapper.toDTO(this.asignacionTurnoRepository.findById(id).get());}

    public void save(AsignacionTurnoDTO dto) {

        AsignacionTurno au;

        if (dto.getIdAsignacion() != null) {
            au = this.asignacionTurnoRepository.findById(dto.getIdAsignacion())
                    .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + dto.getIdAsignacion()));
        } else {
            au = new AsignacionTurno();
        }

        au.setDia(dto.getDia());
        au.setFranja(dto.getFranja());
        if (dto.getHoraInicio() != null) au.setHoraInicio(LocalTime.parse(dto.getHoraInicio()));
        if (dto.getHoraFin() != null) au.setHoraFin(LocalTime.parse(dto.getHoraFin()));


        if (dto.getCampanaResponseDTO() != null && dto.getCampanaResponseDTO().getId() != null) {
            au.setIdCampana(this.campanaRepository.findById(dto.getCampanaResponseDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Campaña no encontrada con ID: " + dto.getCampanaResponseDTO().getId())));
        } else {
            throw new IllegalArgumentException("La campaña es obligatoria para registrar una asignación de turno.");
        }

        if (dto.getVoluntarioDTO() != null && dto.getVoluntarioDTO().getId() != null) {

            var voluntario = this.voluntarioRepository.findById(dto.getVoluntarioDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Voluntario no encontrado con ID: " + dto.getVoluntarioDTO().getId()));

            au.setIdVoluntario(voluntario);

            au.setIdColaborador(voluntario.getIdColaborador());
        } else {
            throw new IllegalArgumentException("El voluntario es obligatorio para registrar una asignación de turno.");
        }

        if (dto.getTiendaResponseDTO() != null && dto.getTiendaResponseDTO().getId() != null) {
            au.setIdTienda(this.tiendaRepository.findById(dto.getTiendaResponseDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Tienda no encontrada con ID: " + dto.getTiendaResponseDTO().getId())));
        } else {
            throw new IllegalArgumentException("La tienda es obligatoria para registrar una asignación de turno.");
        }

        if (dto.getIncidenciaDTO() != null && dto.getIncidenciaDTO().getIdIncidencia() != null) {
            au.setIncidencia(this.incidenciaRepository.findById(dto.getIncidenciaDTO().getIdIncidencia())
                    .orElse(null));
        } else {
            au.setIncidencia(null);
        }

        this.asignacionTurnoRepository.save(au);
    }

    public List<AsignacionTurnoDTO> findByCadenaLocalidad(Integer cadenaId, String localidad) {
        return asignacionTurnoCapitanMapper.toDTOList(this.asignacionTurnoRepository.findByCadenaLocalidad(cadenaId,localidad));
    }

    public List<AsignacionTurnoDTO> findByLocalidad(String localidad) {
        return asignacionTurnoCapitanMapper.toDTOList(this.asignacionTurnoRepository.findByLocalidad(localidad));
    }

    @Transactional
    public void deleteById(Integer id) {
        this.incidenciaRepository.deleteByAsignacionId(id);
        this.asignacionTurnoRepository.deleteById(id);
    }

}
