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
    private final AsignacionTiendasAdminMapper asignacionTiendasAdminMapper;
    private final TiendaAdminMapper tiendaAdminMapper;

    public AsignacionTiendasAdminService(UsuarioRepository usuarioRepository,
                                         TiendaRepository tiendaRepository,
                                         UsuarioTiendaRepository usuarioTiendaRepository,
                                         AsignacionTiendasAdminMapper asignacionTiendasAdminMapper,
                                         TiendaAdminMapper tiendaAdminMapper) {
        this.usuarioRepository = usuarioRepository;
        this.tiendaRepository = tiendaRepository;
        this.usuarioTiendaRepository = usuarioTiendaRepository;
        this.asignacionTiendasAdminMapper = asignacionTiendasAdminMapper;
        this.tiendaAdminMapper = tiendaAdminMapper;
    }

    @Transactional(readOnly = true)
    public List<AsignacionTiendasCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionTiendasRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = buscarCoordinador(idCoordinador);
        List<Integer> idsTiendas = usuarioTiendaRepository.findIdsTiendasByUsuarioId(coordinador.getId());

        return asignacionTiendasAdminMapper.toRequestDTO(coordinador.getId(), idsTiendas);
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
        usuarioTiendaRepository.save(asignacionTiendasAdminMapper.toUsuarioTienda(coordinador, tienda));
    }

    private AsignacionTiendasCoordinadorDTO toResponseDTO(Usuario coordinador) {
        String tiendas = usuarioTiendaRepository.findTiendasAsignadasByUsuarioId(coordinador.getId())
                .stream()
                .map(usuarioTienda -> usuarioTienda.getTienda().getIdCadena().getNombre() + " - " + usuarioTienda.getTienda().getNombre())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin tiendas");

        AsignacionTiendasCoordinadorDTO asignacionTiendasCoordinadorDTO = asignacionTiendasAdminMapper.toDTO(coordinador);
        asignacionTiendasCoordinadorDTO.setTiendasAsignadas(tiendas);

        return asignacionTiendasCoordinadorDTO;
    }
}