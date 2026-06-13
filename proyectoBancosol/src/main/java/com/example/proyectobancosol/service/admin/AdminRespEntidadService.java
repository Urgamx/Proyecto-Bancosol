package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.AsignacionTurnoRepository;
import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.IncidenciaRepository;
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
public class AdminRespEntidadService {

    private final TiendaRepository tiendaRepository;
    private final AsignacionTurnoRepository asignacionTurnoRepository;
    private final ValidacionAdminMapper validacionAdminMapper;

    @Transactional(readOnly = true)
    public List<AdminMenuItemDTO> obtenerMenuRapido(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        
        return tiendas.stream()
                .map(tienda -> {
                    String nombreCadena = (tienda.getIdCadena() != null) ? tienda.getIdCadena().getNombre() : "Sin Cadena";
                    return new AdminMenuItemDTO(
                            tienda.getNombre(),
                            nombreCadena,
                            "/resp-entidad/tienda?id=" + tienda.getId()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidacionBasicaResponseDTO> obtenerResumenDatos(Integer idUsuario) {
        return List.of(
                validarTiendasAsignadas(idUsuario),
                validarVoluntariosAsignados(idUsuario),
                validarIncidenciasRegistradas(idUsuario)
        );
    }

    private ValidacionBasicaResponseDTO validarTiendasAsignadas(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .map(tienda -> {
                    String nombreCadena = (tienda.getIdCadena() != null) ? tienda.getIdCadena().getNombre() : "Sin Cadena";
                    return nombreCadena + " - " + tienda.getNombre();
                })
                .toList();

        return validacionAdminMapper.toDTO("Tiendas", "Tiendas asignadas", elementos);
    }

    private ValidacionBasicaResponseDTO validarVoluntariosAsignados(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .flatMap(tienda -> asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                        // CORRECCIÓN: Usamos getIdVoluntario() como descubrimos antes
                        .filter(asignacion -> asignacion.getIdVoluntario() != null) 
                        .map(asignacion -> asignacion.getIdVoluntario().getNombre() + " - " + tienda.getNombre()))
                .collect(Collectors.toList());

        return validacionAdminMapper.toDTO("Voluntarios", "Voluntarios asignados", elementos);
    }

    private ValidacionBasicaResponseDTO validarIncidenciasRegistradas(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .flatMap(tienda -> asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                        .filter(asignacion -> asignacion.getIncidencia() != null)
                        .map(asignacion -> {
                            String nombreVoluntario = (asignacion.getIdVoluntario() != null) ? asignacion.getIdVoluntario().getNombre() : "Voluntario Desconocido";
                            return nombreVoluntario + " - " + asignacion.getIncidencia().getDescripcion();
                        }))
                .collect(Collectors.toList());

        return validacionAdminMapper.toDTO("Incidencias", "Incidencias registradas", elementos);
    }
}