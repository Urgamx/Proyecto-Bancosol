package com.example.proyectobancosol.service.respTienda;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.IncidenciaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dao.VoluntarioRepository;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Incidencia;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Voluntario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResTiendaService {

    private TiendaRepository tiendaRepository;
    private UsuarioTiendaRepository usuarioTiendaRepository;
    private AsignacionTurnoRepository asignacionTurnoRepository;
    private IncidenciaRepository incidenciaRepository;
    private VoluntarioRepository voluntarioRepository;

    /**
     * Obtener la tienda asignada a un usuario responsable de tienda
     */
    public Tienda obtenerTiendaDelUsuario(Integer idUsuario) {
        return usuarioTiendaRepository.findByUsuarioId(idUsuario)
                .map(ut -> ut.getTienda())
                .orElse(null);
    }

    /**
     * Obtener todas las tiendas del usuario (para responsable tienda siempre será 1)
     */
    public List<Tienda> obtenerTiendasDelUsuario(Integer idUsuario) {
        return tiendaRepository.findTiendasByUsuarioId(idUsuario);
    }

    /**
     * Obtener voluntarios (turnos) asignados a una tienda
     */
    public List<AsignacionTurno> obtenerVoluntariosPorTienda(Integer idTienda) {
        return asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda);
    }

    /**
     * Registrar una incidencia en un turno
     */
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

    /**
     * Actualizar la descripción de una incidencia existente
     */
    public void actualizarIncidencia(Integer idAsignacion, String nuevaDescripcion) {
        AsignacionTurno asignacion = asignacionTurnoRepository.findById(idAsignacion).orElse(null);
        
        if (asignacion != null && asignacion.getIncidencia() != null) {
            asignacion.getIncidencia().setDescripcion(nuevaDescripcion);
            incidenciaRepository.save(asignacion.getIncidencia());
        }
    }

    /**
     * Obtener tienda por ID
     */
    public Tienda obtenerTiendaPorId(Integer idTienda) {
        return tiendaRepository.findById(idTienda).orElse(null);
    }

    /**
     * Obtener voluntario por ID
     */
    public Voluntario obtenerVoluntarioPorId(Integer idVoluntario) {
        return voluntarioRepository.findById(idVoluntario).orElse(null);
    }
}
