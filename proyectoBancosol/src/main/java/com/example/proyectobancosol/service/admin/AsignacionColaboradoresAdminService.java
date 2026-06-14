package com.example.proyectobancosol.service.admin;

import com.example.proyectobancosol.dao.ColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioColaboradorRepository;
import com.example.proyectobancosol.dao.UsuarioRepository;
import com.example.proyectobancosol.dto.request.AsignacionColaboradoresRequestDTO;
import com.example.proyectobancosol.dto.response.AsignacionColaboradoresCoordinadorDTO;
import com.example.proyectobancosol.dto.response.ColaboradorResponseDTO;
import com.example.proyectobancosol.entity.Colaborador;
import com.example.proyectobancosol.entity.Usuario;
import com.example.proyectobancosol.mapper.admin.AsignacionColaboradoresAdminMapper;
import com.example.proyectobancosol.mapper.admin.ColaboradorAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Logica de crud de asignacion de colaboradores en el panel admin.
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * - IA: 25%
 */

@Service
@RequiredArgsConstructor
public class AsignacionColaboradoresAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";
    private static final String SIN_COLABORADORES = "Sin colaboradores";

    private final UsuarioRepository usuarioRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;
    private final AsignacionColaboradoresAdminMapper asignacionColaboradoresAdminMapper;
    private final ColaboradorAdminMapper colaboradorAdminMapper;

    @Transactional(readOnly = true)
    public List<AsignacionColaboradoresCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionColaboradoresRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = buscarCoordinador(idCoordinador);
        return asignacionColaboradoresAdminMapper.toRequestDTO(
                coordinador.getId(),
                usuarioColaboradorRepository.findIdsColaboradoresByUsuarioId(coordinador.getId())
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
    public List<ColaboradorResponseDTO> listarColaboradores() {
        return colaboradorAdminMapper.toDTOList(colaboradorRepository.findByEstadoOrderByNombreEntidadAsc(1));
    }

    @Transactional
    public String guardar(AsignacionColaboradoresRequestDTO request) {
        String error = validar(request);

        if (error != null) {
            return error;
        }

        Usuario coordinador = usuarioRepository.findById(request.getIdCoordinador()).orElseThrow();
        usuarioColaboradorRepository.deleteByUsuarioId(coordinador.getId());

        if (request.getIdsColaboradores() != null) {
            request.getIdsColaboradores().stream()
                    .distinct()
                    .forEach(idColaborador -> guardarRelacion(coordinador, idColaborador));
        }

        return null;
    }

    private String validar(AsignacionColaboradoresRequestDTO request) {
        if (request == null) {
            return "Debes meter los datos de asignacion";
        }

        if (request.getIdCoordinador() == null) {
            return "El coordinador es obligatorio";
        }

        Usuario coordinador = usuarioRepository.findById(request.getIdCoordinador()).orElse(null);

        if (coordinador == null) {
            return "El coordinador no existe";
        }

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            return "El usuario seleccionado no es coordinador";
        }

        if (request.getIdsColaboradores() == null) {
            return null;
        }

        return request.getIdsColaboradores().stream().allMatch(colaboradorRepository::existsById)
                ? null
                : "Uno de los colaboradores seleccionados no existe";
    }

    private void guardarRelacion(Usuario coordinador, Integer idColaborador) {
        Colaborador colaborador = colaboradorRepository.findById(idColaborador).orElseThrow();
        usuarioColaboradorRepository.save(asignacionColaboradoresAdminMapper.toUsuarioColaborador(coordinador, colaborador));
    }

    private AsignacionColaboradoresCoordinadorDTO toResponseDTO(Usuario coordinador) {
        String colaboradores = usuarioColaboradorRepository.findColaboradoresAsignadosByUsuarioId(coordinador.getId()).stream()
                .map(usuarioColaborador -> usuarioColaborador.getColaborador().getNombreEntidad())
                .reduce((a, b) -> a + ", " + b)
                .orElse(SIN_COLABORADORES);

        return asignacionColaboradoresAdminMapper.toDTO(coordinador, colaboradores);
    }
}