/*
Marta Vegas: 100%
 */
package com.example.proyectobancosol.service.respEntidad;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.IncidenciaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.dto.response.AsignacionTurnoResponseDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.dto.response.VoluntarioResponseDTO;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Incidencia;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Voluntario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RespEntidadService {

    private TiendaRepository tiendaRepository;
    private UsuarioTiendaRepository usuarioTiendaRepository;
    private AsignacionTurnoRepository asignacionTurnoRepository;
    private IncidenciaRepository incidenciaRepository;
    private VoluntarioRepository voluntarioRepository;


    private TiendaResponseDTO mapearTienda(Tienda tienda) {
        if (tienda == null) return null;
        TiendaResponseDTO dto = new TiendaResponseDTO();
        dto.setId(tienda.getId());
        dto.setNombre(tienda.getNombre());
        dto.setDireccion(tienda.getDireccion());
        dto.setCadena(tienda.getIdCadena().getNombre());
        dto.setCodPostal(tienda.getCodPostal());
        return dto;
    }

    private VoluntarioResponseDTO mapearVoluntario(Voluntario voluntario) {
        if (voluntario == null) return null;
        VoluntarioResponseDTO dto = new VoluntarioResponseDTO();
        dto.setId(voluntario.getId());
        dto.setNombre(voluntario.getNombre());
        dto.setEmail(voluntario.getEmail());
        dto.setTelefono(voluntario.getTelefono());

        if (voluntario.getIdColaborador() != null) {
            dto.setPerteneceAEntidad(true);
            dto.setNombreEntidad(voluntario.getIdColaborador().getNombreEntidad());
            dto.setEmailEntidad(voluntario.getIdColaborador().getEmail());
            dto.setTelefonoEntidad(voluntario.getIdColaborador().getContactoTlf());
            dto.setDomicilioEntidad(voluntario.getIdColaborador().getDomicilio());
        } else {
            dto.setPerteneceAEntidad(false);
        }
        return dto;
    }

    private AsignacionTurnoResponseDTO mapearAsignacion(AsignacionTurno asignacion) {
        if (asignacion == null) return null;
        AsignacionTurnoResponseDTO dto = new AsignacionTurnoResponseDTO();
        dto.setId(asignacion.getId());
        String horarioCompleto = asignacion.getDia() + " - " + asignacion.getFranja();
        dto.setHorario(horarioCompleto);
        dto.setVoluntario(mapearVoluntario(asignacion.getIdVoluntario()));
        dto.setCampana(asignacion.getIdCampana().getTipoDeCampana().getNombre());

        if (asignacion.getIncidencia() != null) {
            dto.setDescripcionIncidencia(asignacion.getIncidencia().getDescripcion());
        }
        return dto;
    }

    

    public TiendaResponseDTO obtenerTiendaDelUsuario(Integer idUsuario) {
        return usuarioTiendaRepository.findByUsuarioId(idUsuario)
                .map(ut -> mapearTienda(ut.getTienda()))
                .orElse(null);
    }

    public List<TiendaResponseDTO> obtenerTiendasDelUsuario(Integer idUsuario) {
        return tiendaRepository.findTiendasByUsuarioId(idUsuario).stream()
                .map(this::mapearTienda)
                .collect(Collectors.toList());
    }

    public List<AsignacionTurnoResponseDTO> obtenerVoluntariosPorTienda(Integer idTienda) {
        return asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda).stream()
                .map(this::mapearAsignacion)
                .collect(Collectors.toList());
    }

    public void registrarIncidencia(Integer idAsignacion, String descripcion) {
        // Guardar en BD sigue usando Entidades, eso no cambia
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
        return tiendaRepository.findById(idTienda)
                .map(this::mapearTienda)
                .orElse(null);
    }

    public VoluntarioResponseDTO obtenerVoluntarioPorId(Integer idVoluntario) {
        return voluntarioRepository.findById(idVoluntario)
                .map(this::mapearVoluntario)
                .orElse(null);
    }
}