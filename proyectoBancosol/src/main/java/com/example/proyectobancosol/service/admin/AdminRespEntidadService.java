/*
Marta Vegas: 100%
 */

package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.*;
import com.example.proyectobancosol.dto.request.UsuarioRequestDTO;
import com.example.proyectobancosol.dto.response.AdminMenuItemDTO;
import com.example.proyectobancosol.dto.response.ValidacionBasicaResponseDTO;
import com.example.proyectobancosol.entity.*;
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
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<AdminMenuItemDTO> obtenerMenuRapido(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        return tiendas.stream()
                .map(tienda -> {
                    String nombreCadena = (tienda.getIdCadena() != null) ? tienda.getIdCadena().getNombre() : "Sin Cadena";
                    return new AdminMenuItemDTO(tienda.getNombre(), nombreCadena, "/resp-entidad/tienda?id=" + tienda.getId());
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ValidacionBasicaResponseDTO> obtenerResumenDatos(Integer idUsuario) {
        return List.of(validarTiendasAsignadas(idUsuario), validarVoluntariosAsignados(idUsuario), validarIncidenciasRegistradas(idUsuario));
    }

    @Transactional
    public String guardar(UsuarioRequestDTO request, String nombreRol) {
        Usuario usuario = (request.getId() != null)
                ? usuarioRepository.findById(request.getId()).orElse(new Usuario())
                : new Usuario();

        usuario.setNombreCompleto(request.getNombre());
        usuario.setEmail(request.getEmail());

        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
        usuario.setIdRol(rol);

        usuarioRepository.save(usuario);
        return null;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }

    @Transactional(readOnly = true)
    public UsuarioRequestDTO buscarFormulario(Integer id) {
        return usuarioRepository.findById(id).map(UsuarioRequestDTO::new).orElse(new UsuarioRequestDTO());
    }

    @Transactional
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    private ValidacionBasicaResponseDTO validarTiendasAsignadas(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .map(tienda -> (tienda.getIdCadena() != null ? tienda.getIdCadena().getNombre() : "Sin Cadena") + " - " + tienda.getNombre())
                .toList();
        return validacionAdminMapper.toDTO("Tiendas", "Tiendas asignadas", elementos);
    }

    private ValidacionBasicaResponseDTO validarVoluntariosAsignados(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .flatMap(tienda -> asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                        .filter(a -> a.getIdVoluntario() != null)
                        .map(a -> a.getIdVoluntario().getNombre() + " - " + tienda.getNombre()))
                .collect(Collectors.toList());
        return validacionAdminMapper.toDTO("Voluntarios", "Voluntarios asignados", elementos);
    }

    private ValidacionBasicaResponseDTO validarIncidenciasRegistradas(Integer idUsuario) {
        List<Tienda> tiendas = tiendaRepository.findTiendasByUsuarioId(idUsuario);
        List<String> elementos = tiendas.stream()
                .flatMap(tienda -> asignacionTurnoRepository.findAsignacionesByTiendaId(tienda.getId()).stream()
                        .filter(a -> a.getIncidencia() != null)
                        .map(a -> (a.getIdVoluntario() != null ? a.getIdVoluntario().getNombre() : "Voluntario Desconocido") + " - " + a.getIncidencia().getDescripcion()))
                .collect(Collectors.toList());
        return validacionAdminMapper.toDTO("Incidencias", "Incidencias registradas", elementos);
    }
}