package com.example.proyectobancosol.service.capitan;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.IncidenciaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.dto.response.AsignacionTurnoDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.dto.response.VoluntarioDTO;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Incidencia;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Voluntario;
import com.example.proyectobancosol.mapper.capitan.AsignacionTurnoCapitanMapper;
import com.example.proyectobancosol.mapper.capitan.TiendaCapitanMapper;
import com.example.proyectobancosol.mapper.coordinador.VoluntarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la lógica asociada a las operaciones del rol Capitán.
 *
 * Autores:
 * - Carlos Sánchez: 100%
 */

@Service
@AllArgsConstructor
public class CapitanService {

    private final TiendaRepository tiendaRepository;
    private final UsuarioTiendaRepository usuarioTiendaRepository;
    private final AsignacionTurnoRepository asignacionTurnoRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final VoluntarioRepository voluntarioRepository;

    private final TiendaCapitanMapper tiendaMapper;
    private final VoluntarioMapper voluntarioMapper;
    private final AsignacionTurnoCapitanMapper asignacionTurnoMapper;


    public TiendaResponseDTO obtenerTiendaDelUsuario(Integer idUsuario) {
        Tienda tienda = usuarioTiendaRepository.findByUsuarioId(idUsuario)
                .map(ut -> ut.getTienda())
                .orElse(null);

        if (tienda == null) {
            return null;
        }
        return tiendaMapper.toDTO(tienda);
    }

    public List<TiendaResponseDTO> obtenerTiendasDelUsuario(Integer idUsuario) {

        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<TiendaResponseDTO> tiendasDTO = new ArrayList<>();

        for(Tienda tienda : tiendas){
            tiendasDTO.add(tiendaMapper.toDTO(tienda));
        }

        return tiendasDTO;
    }

    public List<AsignacionTurnoDTO> obtenerVoluntariosPorTienda(Integer idTienda) {


        List<AsignacionTurno> turnos = asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda);
        List<AsignacionTurnoDTO> turnosDTO = new ArrayList<>();

        for(AsignacionTurno turno : turnos){
            turnosDTO.add(asignacionTurnoMapper.toDTO(turno));
        }

        return turnosDTO;
    }

    public void registrarIncidencia(Integer idAsignacion, String descripcion) {
        AsignacionTurno asignacion = asignacionTurnoRepository.findById(idAsignacion).orElse(null);

        if (asignacion != null && asignacion.getIncidencia() == null) {
            Incidencia incidencia = new Incidencia();
            incidencia.setAsignacion(asignacion);
            incidencia.setDescripcion(descripcion);

            asignacion.setIncidencia(incidencia);
            incidenciaRepository.save(incidencia);
            asignacionTurnoRepository.save(asignacion);
        }
    }

    public void actualizarIncidencia(Integer idAsignacion, String nuevaDescripcion) {
        AsignacionTurno asignacion = asignacionTurnoRepository.findById(idAsignacion).orElse(null);

        if (asignacion != null && asignacion.getIncidencia() != null) {
            asignacion.getIncidencia().setDescripcion(nuevaDescripcion);
            incidenciaRepository.save(asignacion.getIncidencia());
        }
    }

    public TiendaResponseDTO obtenerTiendaPorId(Integer idTienda) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        return  tiendaMapper.toDTO(tienda);
    }

    public VoluntarioDTO obtenerVoluntarioPorId(Integer idVoluntario) {
        Voluntario voluntario = voluntarioRepository.findById(idVoluntario).orElse(null);
        if (voluntario == null) {
            return null;
        }
        return voluntarioMapper.toDTO(voluntario);
    }
}