package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dto.request.AsignacionTiendasRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionTiendasCoordinadorDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.admin.AsignacionTiendasAdminMapper;
import com.example.proyectobancosol.mapper.admin.TiendaAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Logica de crud de asignacion de tiendas en el panel admin.
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * - IA: 25%
 */

@Service
@RequiredArgsConstructor
public class AsignacionTiendasAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";
    private static final String SIN_TIENDAS = "Sin tiendas";

    private final UsuarioRepository usuarioRepository;
    private final TiendaRepository tiendaRepository;
    private final UsuarioTiendaRepository usuarioTiendaRepository;
    private final AsignacionTiendasAdminMapper asignacionTiendasAdminMapper;
    private final TiendaAdminMapper tiendaAdminMapper;

    @Transactional(readOnly = true)
    public List<AsignacionTiendasCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionTiendasRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = buscarCoordinador(idCoordinador);
        return asignacionTiendasAdminMapper.toRequestDTO(
                coordinador.getId(),
                usuarioTiendaRepository.findIdsTiendasByUsuarioId(coordinador.getId())
        );
    }

    @Transactional(readOnly = true)
    public Usuario buscarCoordinador(Integer idCoordinador) {
        Usuario coordinador = usuarioRepository.findById(idCoordinador).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            throw new IllegalArgumentException("El usuario no es coordinador");
        }

        return coordinador;
    }

    @Transactional(readOnly = true)
    public List<TiendaResponseDTO> listarTiendas() {
        return tiendaAdminMapper.toDTOList(tiendaRepository.findAllConCadena());
    }

    @Transactional
    public String guardar(AsignacionTiendasRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Usuario coordinador = usuarioRepository.findById(request.getIdCoordinador()).orElseThrow();
        usuarioTiendaRepository.deleteByUsuarioId(coordinador.getId());

        if (request.getIdsTiendas() != null) {
            request.getIdsTiendas().stream()
                    .distinct()
                    .forEach(idTienda -> guardarRelacion(coordinador, idTienda));
        }

        return null;
    }

    private String validar(AsignacionTiendasRequestDTO request) {
        if (request == null) {
            return "Los datos de la tienda son obligatorios";
        }

        if (request.getIdCoordinador() == null) {
            return "El coordinador es obligatorio";
        }

        Usuario coordinador = usuarioRepository.findById(request.getIdCoordinador()).orElse(null);

        if (coordinador == null) {
            return "El coordinador no existe";
        }

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            return "El usuario seleccionado no es un coordinador";
        }

        if (request.getIdsTiendas() == null) {
            return null;
        }

        return request.getIdsTiendas().stream().allMatch(tiendaRepository::existsById)
                ? null
                : "Una de las tiendas seleccionadas no existe";
    }

    private void guardarRelacion(Usuario coordinador, Integer idTienda) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElseThrow();
        usuarioTiendaRepository.save(asignacionTiendasAdminMapper.toUsuarioTienda(coordinador, tienda));
    }

    private AsignacionTiendasCoordinadorDTO toResponseDTO(Usuario coordinador) {
        String tiendas = usuarioTiendaRepository.findTiendasAsignadasByUsuarioId(coordinador.getId()).stream()
                .map(usuarioTienda -> usuarioTienda.getTienda().getIdCadena().getNombre() + " - " + usuarioTienda.getTienda().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse(SIN_TIENDAS);

        return asignacionTiendasAdminMapper.toDTO(coordinador, tiendas);
    }
}