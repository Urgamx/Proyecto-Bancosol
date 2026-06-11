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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionColaboradoresAdminService {

    private static final String ROL_COORDINADOR = "COORDINADOR";

    private final UsuarioRepository usuarioRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final UsuarioColaboradorRepository usuarioColaboradorRepository;
    private final AsignacionColaboradoresAdminMapper asignacionColaboradoresAdminMapper;
    private final ColaboradorAdminMapper colaboradorAdminMapper;

    public AsignacionColaboradoresAdminService(UsuarioRepository usuarioRepository,
                                               ColaboradorRepository colaboradorRepository,
                                               UsuarioColaboradorRepository usuarioColaboradorRepository,
                                               AsignacionColaboradoresAdminMapper asignacionColaboradoresAdminMapper,
                                               ColaboradorAdminMapper colaboradorAdminMapper) {
        this.usuarioRepository = usuarioRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.usuarioColaboradorRepository = usuarioColaboradorRepository;
        this.asignacionColaboradoresAdminMapper = asignacionColaboradoresAdminMapper;
        this.colaboradorAdminMapper = colaboradorAdminMapper;
    }

    @Transactional(readOnly = true)
    public List<AsignacionColaboradoresCoordinadorDTO> listar() {
        return usuarioRepository.findByRolNombre(ROL_COORDINADOR)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AsignacionColaboradoresRequestDTO buscarFormulario(Integer idCoordinador) {
        Usuario coordinador = buscarCoordinador(idCoordinador);
        List<Integer> idsColaboradores = usuarioColaboradorRepository.findIdsColaboradoresByUsuarioId(coordinador.getId());

        return asignacionColaboradoresAdminMapper.toRequestDTO(coordinador.getId(), idsColaboradores);
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
    public String guardar(AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO) {
        String error = validar(asignacionColaboradoresRequestDTO);

        if (error != null) {
            return error;
        }

        Usuario coordinador = usuarioRepository.findById(asignacionColaboradoresRequestDTO.getIdCoordinador()).orElseThrow();

        usuarioColaboradorRepository.deleteByUsuarioId(coordinador.getId());

        List<Integer> idsColaboradores = asignacionColaboradoresRequestDTO.getIdsColaboradores();

        if (idsColaboradores == null) {
            idsColaboradores = new ArrayList<>();
        }

        idsColaboradores.stream()
                .distinct()
                .forEach(idColaborador -> guardarRelacion(coordinador, idColaborador));

        return null;
    }

    private String validar(AsignacionColaboradoresRequestDTO asignacionColaboradoresRequestDTO) {
        if (asignacionColaboradoresRequestDTO == null) {
            return "Los datos de asignacion son obligatorios";
        }

        if (asignacionColaboradoresRequestDTO.getIdCoordinador() == null) {
            return "El coordinador es obligatorio";
        }

        if (!usuarioRepository.existsById(asignacionColaboradoresRequestDTO.getIdCoordinador())) {
            return "El coordinador no existe";
        }

        Usuario coordinador = usuarioRepository.findById(asignacionColaboradoresRequestDTO.getIdCoordinador()).orElseThrow();

        if (!ROL_COORDINADOR.equals(coordinador.getIdRol().getNombre())) {
            return "El usuario seleccionado no es coordinador";
        }

        if (asignacionColaboradoresRequestDTO.getIdsColaboradores() != null) {
            for (Integer idColaborador : asignacionColaboradoresRequestDTO.getIdsColaboradores()) {
                if (!colaboradorRepository.existsById(idColaborador)) {
                    return "Uno de los colaboradores seleccionados no existe";
                }
            }
        }

        return null;
    }

    private void guardarRelacion(Usuario coordinador, Integer idColaborador) {
        Colaborador colaborador = colaboradorRepository.findById(idColaborador).orElseThrow();
        usuarioColaboradorRepository.save(asignacionColaboradoresAdminMapper.toUsuarioColaborador(coordinador, colaborador));
    }

    private AsignacionColaboradoresCoordinadorDTO toResponseDTO(Usuario coordinador) {
        String colaboradores = usuarioColaboradorRepository.findColaboradoresAsignadosByUsuarioId(coordinador.getId())
                .stream()
                .map(usuarioColaborador -> usuarioColaborador.getColaborador().getNombreEntidad())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin colaboradores");

        AsignacionColaboradoresCoordinadorDTO asignacionColaboradoresCoordinadorDTO = asignacionColaboradoresAdminMapper.toDTO(coordinador);
        asignacionColaboradoresCoordinadorDTO.setColaboradoresAsignados(colaboradores);

        return asignacionColaboradoresCoordinadorDTO;
    }
}