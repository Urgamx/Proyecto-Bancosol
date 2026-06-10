package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.TiendaRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dao.UsuarioTiendaRepository;
import com.example.proyectobancosol.dto.request.AsignacionTiendasRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionTiendasCoordinadorDTO;
import com.example.proyectobancosol.dto.response.TiendaResponseDTO;
import com.example.proyectobancosol.entity.Tienda;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.entity.UsuarioTienda;
import com.example.proyectobancosol.entity.UsuarioTiendaId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionTiendasAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";

    private final UsuarioRepository usuarioRepository;
    private final TiendaRepository tiendaRepository;
    private final UsuarioTiendaRepository usuarioTiendaRepository;

    public AsignacionTiendasAdminService(UsuarioRepository usuarioRepository,
                                         TiendaRepository tiendaRepository,
                                         UsuarioTiendaRepository usuarioTiendaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tiendaRepository = tiendaRepository;
        this.usuarioTiendaRepository = usuarioTiendaRepository;
    }

    @Transactional(readOnly = true)
    public List<AsignacionTiendasCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionTiendasRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = usuarioRepository.findById(idCoordinador).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            throw new IllegalArgumentException("El usuario no es coordinador");
        }

        List<Integer> idsTiendas = usuarioTiendaRepository.findIdsTiendasByUsuarioId(idCoordinador);

        return new AsignacionTiendasRequestDTO(
                idCoordinador,
                idsTiendas
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
        return tiendaRepository.findAllConCadena()
                .stream()
                .map(tienda -> new TiendaResponseDTO(
                        tienda.getId(),
                        tienda.getIdCadena().getNombre(),
                        tienda.getNombre(),
                        tienda.getDireccion(),
                        tienda.getCodPostal()
                ))
                .toList();
    }

    @Transactional
    public String guardar(AsignacionTiendasRequestDTO asignacionTiendasRequestDTO) {
        String error = validar(asignacionTiendasRequestDTO);

        if (error != null) {
            return error;
        }

        Usuario coordinador = usuarioRepository.findById(asignacionTiendasRequestDTO.getIdCoordinador()).orElseThrow();

        usuarioTiendaRepository.deleteByUsuarioId(coordinador.getId());

        List<Integer> idsTiendas = asignacionTiendasRequestDTO.getIdsTiendas();

        if (idsTiendas == null) {
            idsTiendas = new ArrayList<>();
        }

        idsTiendas.stream()
                .distinct()
                .forEach(idTienda -> guardarRelacion(coordinador, idTienda));

        return null;
    }

    private String validar(AsignacionTiendasRequestDTO asignacionTiendasRequestDTO) {
        if (asignacionTiendasRequestDTO == null) {
            return "Los datos de asignacion son obligatorios";
        }

        if (asignacionTiendasRequestDTO.getIdCoordinador() == null) {
            return "El coordinador es obligatorio";
        }

        if (!usuarioRepository.existsById(asignacionTiendasRequestDTO.getIdCoordinador())) {
            return "El coordinador no existe";
        }

        Usuario coordinador = usuarioRepository.findById(asignacionTiendasRequestDTO.getIdCoordinador()).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            return "El usuario seleccionado no es coordinador";
        }

        if (asignacionTiendasRequestDTO.getIdsTiendas() != null) {
            for (Integer idTienda : asignacionTiendasRequestDTO.getIdsTiendas()) {
                if (!tiendaRepository.existsById(idTienda)) {
                    return "Una de las tiendas seleccionadas no existe";
                }
            }
        }

        return null;
    }

    private void guardarRelacion(Usuario coordinador, Integer idTienda) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElseThrow();

        UsuarioTiendaId usuarioTiendaId = new UsuarioTiendaId();
        usuarioTiendaId.setIdUsuario(coordinador.getId());
        usuarioTiendaId.setIdTienda(tienda.getId());

        UsuarioTienda usuarioTienda = new UsuarioTienda();
        usuarioTienda.setId(usuarioTiendaId);
        usuarioTienda.setUsuario(coordinador);
        usuarioTienda.setTienda(tienda);

        usuarioTiendaRepository.save(usuarioTienda);
    }

    private AsignacionTiendasCoordinadorDTO convertirAResponseDTO(Usuario coordinador) {
        String tiendas = usuarioTiendaRepository.findTiendasAsignadasByUsuarioId(coordinador.getId())
                .stream()
                .map(usuarioTienda -> usuarioTienda.getTienda().getIdCadena().getNombre() + " - " + usuarioTienda.getTienda().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin tiendas");

        return new AsignacionTiendasCoordinadorDTO(
                coordinador.getId(),
                coordinador.getNombreCompleto(),
                coordinador.getEmail(),
                tiendas
        );
    }
}