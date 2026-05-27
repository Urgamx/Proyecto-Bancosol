package com.example.proyectobancosol.service.respEntidad;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.IncidenciaRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Incidencia;
import com.example.proyectobancosol.entity.Tienda;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RespEntidadService {

    private TiendaRepository tiendaRepository;
    private UsuarioTiendaRepository usuarioTiendaRepository;
    private AsignacionTurnoRepository asignacionTurnoRepository;
    private IncidenciaRepository incidenciaRepository;

    public Tienda obtenerTiendaDelUsuario(Integer idUsuario) {
        return usuarioTiendaRepository.findByUsuarioId(idUsuario)
                .map(ut -> ut.getTienda())
                .orElse(null);
    }

    public List<Tienda> obtenerTiendasDelUsuario(Integer idUsuario) {
        return tiendaRepository.findTiendasByUsuarioId(idUsuario);
    }

    public List<AsignacionTurno> obtenerVoluntariosPorTienda(Integer idTienda) {
        return asignacionTurnoRepository.findAsignacionesByTiendaId(idTienda);
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

    public Tienda obtenerTiendaPorId(Integer idTienda) {
        return tiendaRepository.findById(idTienda).orElse(null);
    }
}
