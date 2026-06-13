package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import com.example.proyectobancosol.dto.response.ValidacionBasicaResponseDTO;
import com.example.proyectobancosol.entity.AsignacionTurno;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.mapper.admin.ValidacionAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminRespTiendaService {

    private final UsuarioTiendaRepository usuarioTiendaRepository;
    private final AsignacionTurnoRepository asignacionTurnoRepository;
    private final ValidacionAdminMapper validacionAdminMapper;

    @Transactional(readOnly = true)
    public Tienda obtenerTiendaAsignada(Integer idUsuario) {
        return usuarioTiendaRepository.findByUsuarioId(idUsuario)
                .map(ut -> ut.getTienda())
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<AdminMenuItemDTO> obtenerMenuRapido(Integer idUsuario) {
        Tienda tienda = obtenerTiendaAsignada(idUsuario);
        
        if (tienda != null) {
            String nombreCadena = (tienda.getIdCadena() != null) ? tienda.getIdCadena().getNombre() : "Sin Cadena";
            return List.of(
                    new AdminMenuItemDTO(
                            "Mi Tienda",
                            nombreCadena + " - " + tienda.getNombre(),
                            "/resp-tienda/tienda?id=" + tienda.getId()
                    ),
                    new AdminMenuItemDTO(
                            "Voluntarios",
                            "Ver voluntarios asignados",
                            "/resp-tienda/voluntarios?id=" + tienda.getId()
                    )
            );
        }
        
        return List.of();
    }

    @Transactional(readOnly = true)
    public List<ValidacionBasicaResponseDTO> obtenerResumenDatos(Integer idUsuario) {
        return List.of(
                validarTiendaAsignada(idUsuario),
                validarVoluntariosAsignados(idUsuario),
                validarIncidenciasRegistradas(idUsuario)
        );
    }

    private ValidacionBasicaResponseDTO validarTiendaAsignada(Integer idUsuario) {
        Tienda tienda = obtenerTiendaAsignada(idUsuario);
        
        if (tienda != null) {
            String nombreCadena = (tienda.getIdCadena() != null) ? tienda.getIdCadena().getNombre() : "Sin Cadena";
            List<String> elementos = List.of(nombreCadena + " - " + tienda.getNombre());
            return validacionAdminMapper.toDTO("Tienda", "Tienda asignada", elementos);
        }
        
        return validacionAdminMapper.toDTO("Tienda", "Tienda asignada", List.of());
    }

    private ValidacionBasicaResponseDTO validarVoluntariosAsignados(Integer idUsuario) {
        Tienda tienda = obtenerTiendaAsignada(idUsuario);
        
        if (tienda != null) {
            List<String> elementos = asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                    .filter(asignacion -> asignacion.getIdVoluntario() != null)
                    .map(asignacion -> asignacion.getIdVoluntario().getNombre()) 
                    .collect(Collectors.toList());

            return validacionAdminMapper.toDTO("Voluntarios", "Voluntarios asignados", elementos);
        }
        
        return validacionAdminMapper.toDTO("Voluntarios", "Voluntarios asignados", List.of());
    }

    private ValidacionBasicaResponseDTO validarIncidenciasRegistradas(Integer idUsuario) {
        Tienda tienda = obtenerTiendaAsignada(idUsuario);
        
        if (tienda != null) {
            List<String> elementos = asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                    .filter(asignacion -> asignacion.getIncidencia() != null && asignacion.getIdVoluntario() != null)
                    .map(asignacion -> asignacion.getIdVoluntario().getNombre() + " - " + 
                            asignacion.getIncidencia().getDescripcion())
                    .collect(Collectors.toList());

            return validacionAdminMapper.toDTO("Incidencias", "Incidencias registradas", elementos);
        }
        
        return validacionAdminMapper.toDTO("Incidencias", "Incidencias registradas", List.of());
    }
}